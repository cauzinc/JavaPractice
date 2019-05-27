package com.myMall.controller.portal;

import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.demo.trade.config.Configs;
import com.myMall.common.Const;
import com.myMall.common.ServerResponse;
import com.myMall.pojo.User;
import com.myMall.service.IOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


@Controller
@RequestMapping("/order/")
public class OrderController {
    private Logger logger = LoggerFactory.getLogger(OrderController.class);
    private IOrderService iOrderService;
    @Autowired
    public void setiOrderService(IOrderService iOrderService) {
        this.iOrderService = iOrderService;
    }

    /**
     * 生成订单二维码和二维码信息并返回到前端
      * @param session
     * @param orderNo
     * @param request
     * @return Map<String, String> 订单号，二维码地址
     */
    @ResponseBody
    @RequestMapping(value = "pay.do", method = RequestMethod.POST)
    public ServerResponse pay(HttpSession session, Long orderNo, HttpServletRequest request) {
        // 获取运行时项目的真实路径
        String path = request.getSession().getServletContext().getRealPath("upload");
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        return iOrderService.pay(user.getId(), orderNo, path);
    }

    /**
     * deal callback from alipay
     * @param request httpRequest from alipay will package all params in request
     * @return
     */
    @ResponseBody
    @RequestMapping("alipay_callback.do")
    public Object alipayCallback(HttpServletRequest request) {
        // 只能用这种方式来获取alipay传给我们的参数
        Map requestParams = request.getParameterMap();
        Map<String, String> params = new HashMap<>();

        // 参数处理
        for(Iterator iter = requestParams.keySet().iterator(); iter.hasNext() ;) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = String.join(",", values);
            params.put(name, valueStr);
        }

        // 打印支付宝签名， 交易状态， 以及参数
        logger.info("支付宝回调,sign:{},trade_status:{},参数:{}",params.get("sign"),params.get("trade_status"),params.toString());

        return iOrderService.aliCallback(params).isSuccess() ?
                Const.AlipayCallback.RESPONSE_SUCCESS : Const.AlipayCallback.RESPONSE_FAILED;
    }

    @ResponseBody
    @RequestMapping(value = "query_order_status.do", method = RequestMethod.GET)
    public ServerResponse<Boolean> queryOrderStatus(Long orderNo) {
        boolean ifOrderPaid = iOrderService.queryOrderStatus(orderNo).isSuccess();
        return ServerResponse.createBySuccess(ifOrderPaid);
    }

    @ResponseBody
    @RequestMapping(value = "create_order.do", method = RequestMethod.POST)
    public ServerResponse createOrder(HttpSession session, Integer shippingId) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        return iOrderService.createOrder(user.getId(), shippingId);
    }

}
