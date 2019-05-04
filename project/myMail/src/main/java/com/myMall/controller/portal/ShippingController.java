package com.myMall.controller.portal;

import com.myMall.common.Const;
import com.myMall.common.ServerResponse;
import com.myMall.pojo.Shipping;
import com.myMall.pojo.User;
import com.myMall.service.IShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/shipping/")
public class ShippingController {
    private IShippingService iShippingService;

    @Autowired
    public void setiShippingService(IShippingService iShippingService) {
        this.iShippingService = iShippingService;
    }

    @ResponseBody
    @RequestMapping(value = "add.do", method = RequestMethod.POST)
    public ServerResponse add(HttpSession session, Shipping shipping) {
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        return iShippingService.add(user.getId(), shipping);
    }

    @ResponseBody
    @RequestMapping(value = "update.do", method = RequestMethod.POST)
    public ServerResponse update(HttpSession session, Shipping updateItem) {
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        return iShippingService.update(user.getId(), updateItem);
    }

    @ResponseBody
    @RequestMapping(value = "delete.do", method = RequestMethod.POST)
    public ServerResponse delete(HttpSession session, Integer shippingId) {
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        return iShippingService.delete(user.getId(), shippingId);
    }

    @ResponseBody
    @RequestMapping(value = "select.do", method = RequestMethod.GET)
    public ServerResponse select(HttpSession session, Integer shippingId) {
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        return iShippingService.select(user.getId(), shippingId);
    }

    @ResponseBody
    @RequestMapping(value = "select_all.do", method = RequestMethod.GET)
    public ServerResponse selectAll(HttpSession session, int pageNum, int pageSize) {
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        return iShippingService.selectAll(pageNum, pageSize, user.getId());
    }
}
