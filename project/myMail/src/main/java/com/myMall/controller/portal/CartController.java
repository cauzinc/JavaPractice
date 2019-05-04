package com.myMall.controller.portal;

import com.myMall.common.Const;
import com.myMall.common.ServerResponse;
import com.myMall.pojo.User;
import com.myMall.service.impl.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/cart/")
public class CartController {
    private CartService iCartService;
    @Autowired
    public void setiCartService(CartService iCartService) {
        this.iCartService = iCartService;
    }

    @ResponseBody
    @RequestMapping(value = "add.do", method = RequestMethod.POST)
    public ServerResponse add(HttpSession session, Integer productId,
                              @RequestParam(value = "count", defaultValue = "1") Integer count) {
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null) {
            return ServerResponse.createByErrorByMessage("请先登录");
        }

        return iCartService.add(user.getId(), productId, count);
    }
}
