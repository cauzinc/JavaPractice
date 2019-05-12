package com.myMall.service.impl;

import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.internal.util.AlipayUtils;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.alipay.demo.trade.config.Configs;
import com.alipay.demo.trade.model.result.AlipayF2FPrecreateResult;
import com.alipay.demo.trade.utils.ZxingUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.myMall.common.Const;
import com.myMall.common.ServerResponse;
import com.myMall.dao.OrderItemMapper;
import com.myMall.dao.OrderMapper;
import com.myMall.dao.PayInfoMapper;
import com.myMall.pojo.Order;
import com.myMall.pojo.OrderItem;
import com.myMall.pojo.PayInfo;
import com.myMall.service.IOrderService;
import com.myMall.util.AlipayUtil;
import com.myMall.util.DateTimeUtil;
import com.myMall.util.FTPUtil;
import com.myMall.util.PropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.Map;

@Service("iOrderService")
public class OrderService implements IOrderService {
    private OrderMapper orderMapper;
    private PayInfoMapper payInfoMapper;
    private OrderItemMapper orderItemMapper;
    private static Logger logger = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    public void setPayInfoMapper(PayInfoMapper payInfoMapper) {
        this.payInfoMapper = payInfoMapper;
    }
    @Autowired
    public void setOrderMapper(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }
    @Autowired
    public void setOrderItemMapper(OrderItemMapper orderItemMapper) {
        this.orderItemMapper = orderItemMapper;
    }

    public ServerResponse pay(Integer userId, Long orderNo, String path) {
        Map<String, String> resultMap = Maps.newHashMap();

        // todo 获取订单信息，检查订单是否存在
        Order order = orderMapper.selectByUserIdAndOrderNo(userId, orderNo);
        if(order == null) {
            return ServerResponse.createBySuccessByMessage("订单不存在");
        }
        resultMap.put("orderNo", order.getOrderNo().toString());

        // todo 调用支付宝接口, 获取response
        List<OrderItem> orderItemList = orderItemMapper.getByOrderNoUserId(orderNo, userId);
        AlipayF2FPrecreateResult result = AlipayUtil.createQrCode(order, orderItemList);

        String errorMsg = "";
        switch (result.getTradeStatus()) {
            case SUCCESS:

                logger.info("支付宝预下单成功: )");

                AlipayTradePrecreateResponse response = result.getResponse();
                AlipayUtil.dumpResponse(response);

                // todo 把二维码图片上传到自己的ftp服务器
                File fileDir = new File(path);
                if(!fileDir.exists()) {
                    fileDir.setWritable(true);
                    fileDir.mkdirs();
                }

                // 需要修改为运行机器上的路径
                String filePath = String.format(path + "/qr-%s.png", response.getOutTradeNo());
                String fileName = String.format("qr-%s.png", response.getOutTradeNo());
                logger.info("filePath:" + filePath);

                // 这有什么用？？不处理返回值吗？？
                ZxingUtils.getQRCodeImge(response.getQrCode(), 256, filePath);
                File targetFile = new File(path, fileName); // 这个file对象不还是空的吗??

                try {
                    FTPUtil.uploadFile(Lists.newArrayList(targetFile));
                } catch (Exception e) {
                    logger.error("上传二维码异常",e);
                }

                String qrUrl = PropertiesUtil.getProperty("ftp.server.http.prefix")+targetFile.getName();
                resultMap.put("qrUrl",qrUrl);
                return ServerResponse.createBySuccess(resultMap);

            case FAILED:
                errorMsg = "支付宝预下单失败!!!";
                break;

            case UNKNOWN:
                errorMsg = "系统异常，预下单状态未知!!!";
                break;

            default:
                errorMsg = "不支持的交易状态，交易返回异常!!!";
                break;
        }

        logger.error(errorMsg);
        return ServerResponse.createByErrorByMessage(errorMsg);
    }


    public ServerResponse aliCallback(Map<String, String> params) {
        Long orderNo = Long.valueOf(params.get("out_trade_no"));
        String tradeNo = params.get("trade_no");
        String tradeStatus = params.get("trade_status");
        Order order = orderMapper.selectOrderByOrderNo(orderNo);
        if(order == null) {
            return  ServerResponse.createByErrorByMessage("没有该订单, 忽略该回调");
        }
        // todo 验证回调是否合法(重要)
        try{
            params.remove("sign_type"); // 要去掉这个参数, 签名类型需要我们手动传递
            boolean alipayRSACheckedV2 = AlipaySignature.rsaCheckV2(params, Configs.getAlipayPublicKey(), "utf-8", Configs.getSignType());  // 通过ali sdk中提供的Configs对象我们可以获取某些关键参数
            if(!alipayRSACheckedV2) {
                return ServerResponse.createByErrorByMessage("非法请求！");
            }
        } catch (Exception e) {
            logger.error("支付宝验证回调异常");
            e.printStackTrace();
        }

        // todo 避免重复通知(重要), 重复通知的状态也是success
        if(order.getStatus() >= Const.OrderStatusEnum.PAID.getCode()) {
            return ServerResponse.createBySuccess("支付宝重复调用");
        }

        // 更新订单状态
        if(Const.AlipayCallback.TRADE_STATUS_TRADE_SUCCESS.equals(tradeStatus)) {
            order.setPaymentTime(DateTimeUtil.strToDate(params.get("gmt_payment")));
            order.setStatus(Const.OrderStatusEnum.PAID.getCode());
            orderMapper.updateByPrimaryKeySelective(order);
        }

        // 生成支付信息PayInfo
        PayInfo payInfo = new PayInfo();
        payInfo.setOrderNo(order.getOrderNo());
        payInfo.setPayPlatform(Const.PayPlatformEnum.ALIPAY.getCode());
        payInfo.setPlatformNumber(tradeNo);
        payInfo.setPlatformStatus(tradeStatus);
        payInfo.setUserId(order.getUserId());
        payInfoMapper.insert(payInfo);

        return ServerResponse.createBySuccess();
    }

    public ServerResponse<Boolean> queryOrderStatus(Long orderNo) {
        Order order = orderMapper.selectOrderByOrderNo(orderNo);
        if(order == null) {
            return ServerResponse.createByErrorByMessage("订单不存在");
        }
        if(order.getStatus() >= Const.OrderStatusEnum.PAID.getCode()) {
            return ServerResponse.createBySuccess(true);
        } else {
            return ServerResponse.createByError();
        }
    }

}
