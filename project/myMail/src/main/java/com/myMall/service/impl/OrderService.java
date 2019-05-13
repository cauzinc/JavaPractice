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
import com.myMall.dao.*;
import com.myMall.pojo.*;
import com.myMall.service.IOrderService;
import com.myMall.util.*;
import com.myMall.vo.OrderItemVo;
import com.myMall.vo.OrderVo;
import com.myMall.vo.ShippingVo;
import net.sf.jsqlparser.schema.Server;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.math.BigDecimal;
import java.util.*;

@Service("iOrderService")
public class OrderService implements IOrderService {
    private OrderMapper orderMapper;
    private PayInfoMapper payInfoMapper;
    private CartMapper cartMapper;
    private OrderItemMapper orderItemMapper;
    private ProductMapper productMapper;
    private ShippingMapper shippingMapper;
    private static Logger logger = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    public void setPayInfoMapper(PayInfoMapper payInfoMapper) {
        this.payInfoMapper = payInfoMapper;
    }
    @Autowired
    public void setCartMapper(CartMapper cartMapper) {
        this.cartMapper = cartMapper;
    }
    @Autowired
    public void setOrderMapper(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }
    @Autowired
    public void setOrderItemMapper(OrderItemMapper orderItemMapper) {
        this.orderItemMapper = orderItemMapper;
    }
    @Autowired
    public void setProductMapper(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }
    @Autowired
    public void setShippingMapper(ShippingMapper shippingMapper) {
        this.shippingMapper = shippingMapper;
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

    /**
     * create order
     * @param userId
     * @param shippingId
     * @return
     */
    public ServerResponse createOrder(Integer userId, Integer shippingId) {
        // todo 获取用户的购物车已选商品的信息
        List<Cart> cartList = cartMapper.selectCheckedListByUserId(userId);

        // todo 生成每件商品的订单OrderItem，此时还没有Order，所以暂时不插入到数据库
        ServerResponse<List<OrderItem>> response = this.getOrderItemList(userId, cartList);
        if(!response.isSuccess()) {
            return response;
        }
        List<OrderItem> orderItemList = response.getData();
        if(CollectionUtils.isEmpty(orderItemList)) {
            return ServerResponse.createByErrorByMessage("购物车为空");
        }

        // todo 计算订单总价并生成订单Order, 然后关联Order 与 OrderItem, 并将数据插入到数据库
        BigDecimal totalPrice = this.getTotalPrice(orderItemList);
        Order order = this.assembleOrder(userId, shippingId, totalPrice);
        if(order == null) {
            return ServerResponse.createByErrorByMessage("生成订单失败");
        }
        for(OrderItem orderItem : orderItemList) {
            orderItem.setOrderNo(order.getOrderNo());
        }
        orderItemMapper.batchInsert(orderItemList);

        // todo 生成成功后更新用户下单的各项商品的信息
        this.updateProductsStock(orderItemList);

        // todo 清空用户的购物车
        this.clearUserCart(cartList);

        // todo 组装OrderVo返回给前端
        OrderVo orderVo = this.assembleOrderVo(order, orderItemList);
        return ServerResponse.createBySuccess(orderVo);
    }

    // 根据用户购物车情况生成orderItemList
    private ServerResponse<List<OrderItem>> getOrderItemList(int userId, List<Cart> cartList) {
        if(CollectionUtils.isEmpty(cartList)) {
            return ServerResponse.createByErrorByMessage("购物车为空");
        }

        List<OrderItem> orderItemList = new ArrayList<>();

        for(Cart cart : cartList) {
            // 检查产品状态和库存
            Product product = productMapper.selectByPrimaryKey(cart.getProductId());
            if(Const.ProductStatement.OFFBOARD.getState().equals(product.getStatus())) {
                return ServerResponse.createByErrorByMessage("产品" + product.getName() + "已下架");
            }

            if(product.getStock() < cart.getQuantity()) {
                return ServerResponse.createByErrorByMessage("产品" + product.getName() + "库存不足");
            }

            OrderItem orderItem = new OrderItem();
            orderItem.setUserId(userId);
            orderItem.setProductId(cart.getProductId());
            orderItem.setProductName(product.getName());
            orderItem.setProductImage(product.getMainImage());
            orderItem.setCurrentUnitPrice(product.getPrice());
            orderItem.setQuantity(cart.getQuantity());
            orderItem.setTotalPrice(BigDecimalUtil.multi(product.getPrice().doubleValue(), cart.getQuantity().doubleValue()));
            orderItemList.add(orderItem);
        }

        return ServerResponse.createBySuccess(orderItemList);
    }

    // 根据orderItemList计算总价
    private BigDecimal getTotalPrice(List<OrderItem> orderItemList) {
        BigDecimal totalPrice = new BigDecimal("0");
        for(OrderItem orderItem : orderItemList) {
            totalPrice = BigDecimalUtil.add(orderItem.getTotalPrice().doubleValue(), totalPrice.doubleValue());
        }
        return totalPrice;
    }

    private Order assembleOrder(int userId, int shippingId, BigDecimal totalPrice) {
        Order order = new Order();
        order.setPayment(totalPrice);
        order.setStatus(Const.OrderStatusEnum.WAIT_PAY.getCode());
        order.setPostage(0);
        order.setOrderNo(this.createOrderNo());
        order.setPaymentType(Const.PaymentTypeEnum.ONLINE_PAY.getCode());
        order.setShippingId(shippingId);
        order.setUserId(userId);
        int rowCount = orderMapper.insert(order);
        return rowCount > 0 ? order : null;
    }

    private void updateProductsStock(List<OrderItem> orderItemList) {
        for(OrderItem orderItem : orderItemList) {
            Product product = productMapper.selectByPrimaryKey(orderItem.getProductId());
            product.setStock(orderItem.getQuantity());
            productMapper.updateByPrimaryKeySelective(product);
        }
    }

    private void clearUserCart(List<Cart> cartList) {
        for(Cart cart : cartList) {
            cartMapper.deleteByPrimaryKey(cart.getId());
        }
    }

    private OrderVo assembleOrderVo(Order order, List<OrderItem> orderItemList) {
        OrderVo orderVo = new OrderVo();
        orderVo.setOrderNo(order.getOrderNo());
        orderVo.setImageHost(PropertiesUtil.getProperty("ftp.server.http.prefix"));
        orderVo.setPayment(order.getPayment());
        orderVo.setPaymentType(order.getPaymentType());
        orderVo.setPaymentTypeDesc(Const.PaymentTypeEnum.codeOf(order.getStatus()).getValue());

        orderVo.setPostage(order.getPostage());
        orderVo.setStatus(order.getStatus());

        Shipping shipping =  shippingMapper.selectByPrimaryKey(order.getShippingId());
        orderVo.setShippingId(order.getShippingId());
        orderVo.setReceiverName(shipping.getReceiverName());
        orderVo.setShippingVo(this.assembleShippingVo(shipping));

        List<OrderItemVo> orderItemVoList = new ArrayList<>();
        for(OrderItem orderItem : orderItemList) {
            orderItemVoList.add(assembleOrderItemVo(orderItem));
        }
        orderVo.setOrderItemVoList(orderItemVoList);

        return orderVo;
    }

    private OrderItemVo assembleOrderItemVo(OrderItem orderItem) {
        OrderItemVo orderItemVo = new OrderItemVo();
        orderItemVo.setOrderNo(orderItem.getOrderNo());
        orderItemVo.setCurrentUnitPrice(orderItem.getCurrentUnitPrice());
        orderItemVo.setProductId(orderItem.getProductId());
        orderItemVo.setQuantity(orderItem.getQuantity());
        orderItemVo.setTotalPrice(orderItem.getTotalPrice());
        orderItemVo.setProductName(orderItem.getProductName());
        orderItemVo.setProductImage(orderItem.getProductImage());
        orderItemVo.setCreateTime(DateTimeUtil.dateToStr(orderItem.getCreateTime()));

        return orderItemVo;
    }

    private ShippingVo assembleShippingVo(Shipping shipping) {
        ShippingVo shippingVo = new ShippingVo();
        shippingVo.setReceiverName(shipping.getReceiverName());
        shippingVo.setReceiverAddress(shipping.getReceiverAddress());
        shippingVo.setReceiverCity(shipping.getReceiverCity());
        shippingVo.setReceiverProvince(shipping.getReceiverProvince());
        shippingVo.setReceiverDistrict(shipping.getReceiverDistrict());
        shippingVo.setReceiverMobile(shipping.getReceiverMobile());
        shippingVo.setReceiverZip(shipping.getReceiverZip());
        shippingVo.setReceiverPhone(shipping.getReceiverPhone());
        return shippingVo;
    }

    // 简单地生成订单号，在高并发和分布式系统的情况下，要采取其他方式来生成订单号
    private long createOrderNo() {
        long currentTime = new Date().getTime();
        return currentTime + new Random().nextInt(100);
    }
}
