package com.myMall.controller.portal;

import com.myMall.common.ServerResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/order/")
public class OrderController {

    public ServerResponse pay(HttpSession session, Long orderNo, HttpServletRequest request) {

        // 获取运行时项目的真实路径
        String path = request.getSession().getServletContext().getRealPath("upload");

        return null;
    }



}
