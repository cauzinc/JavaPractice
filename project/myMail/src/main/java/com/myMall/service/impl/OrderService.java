package com.myMall.service.impl;

import com.alipay.api.internal.util.AlipayUtils;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.alipay.demo.trade.model.result.AlipayF2FPrecreateResult;
import com.alipay.demo.trade.utils.ZxingUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.myMall.common.ServerResponse;
import com.myMall.dao.OrderItemMapper;
import com.myMall.dao.OrderMapper;
import com.myMall.pojo.Order;
import com.myMall.pojo.OrderItem;
import com.myMall.service.IOrderService;
import com.myMall.util.AlipayUtil;
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
    private OrderItemMapper orderItemMapper;
    private static Logger logger = LoggerFactory.getLogger(OrderService.class);
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

                // 这有什么用？？
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

}
