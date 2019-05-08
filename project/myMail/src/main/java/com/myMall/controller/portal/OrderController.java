package com.myMall.controller.portal;

import com.myMall.common.ServerResponse;
import com.myMall.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/order/")
public class OrderController {
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
    public ServerResponse pay(HttpSession session, Long orderNo, HttpServletRequest request) {
        // 获取运行时项目的真实路径
        String path = request.getSession().getServletContext().getRealPath("upload");

        return null;
    }



}
