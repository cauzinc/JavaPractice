package com.myMall.controller.portal;

import com.myMall.common.Const;
import com.myMall.common.ServerResponse;
import com.myMall.pojo.User;
import com.myMall.service.ICartService;
import com.myMall.service.impl.CartService;
import com.myMall.vo.CartVo;
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
    private ICartService iCartService;
    @Autowired
    public void setiCartService(CartService iCartService) {
        this.iCartService = iCartService;
    }

    @ResponseBody
    @RequestMapping(value = "add.do", method = RequestMethod.POST)
    public ServerResponse add(HttpSession session, Integer productId,
                              @RequestParam(value = "count", defaultValue = "1") Integer count) {
        User user = (User)session.getAttribute(Const.CURRENT_USER);

        return iCartService.add(user.getId(), productId, count);
    }

    @ResponseBody
    @RequestMapping(value = "update.do", method = RequestMethod.POST)
    public ServerResponse update(HttpSession session, Integer productId,
                              @RequestParam(value = "count", defaultValue = "1") Integer count) {
        User user = (User)session.getAttribute(Const.CURRENT_USER);

        return iCartService.update(user.getId(), productId, count);
    }

    @ResponseBody
    @RequestMapping(value = "delete.do", method = RequestMethod.POST)
    public ServerResponse delete(HttpSession session, Integer[] productIds) {
        User user = (User)session.getAttribute(Const.CURRENT_USER);

        return iCartService.delete(user.getId(), productIds);
    }


    @ResponseBody
    @RequestMapping(value = "select_one.do", method = RequestMethod.POST)
    public ServerResponse selectOne(HttpSession session, Integer productId,
                                 @RequestParam(value = "checked", defaultValue = "1") Integer checked) {
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        return iCartService.selectOrUnselect(user.getId(), productId,  checked);
    }

    @ResponseBody
    @RequestMapping(value = "select_all.do", method = RequestMethod.POST)
    public ServerResponse selectAll(HttpSession session,
                                 @RequestParam(value = "checked", defaultValue = "1") Integer checked) {
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        return iCartService.selectOrUnselect(user.getId(), null,  checked);
    }

    @ResponseBody
    @RequestMapping(value = "get_count.do", method = RequestMethod.GET)
    public ServerResponse getProductCountInCart(HttpSession session) {
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        return iCartService.getProductCountInCart(user.getId());
    }

}




