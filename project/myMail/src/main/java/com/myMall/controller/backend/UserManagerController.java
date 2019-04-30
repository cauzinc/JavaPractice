package com.myMall.controller.backend;

import com.myMall.common.Const;
import com.myMall.common.ServerResponse;
import com.myMall.pojo.User;
import com.myMall.service.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value="/admin/user/")
public class UserManagerController {
    @Autowired
    private IUserService iUserService;

    @RequestMapping(value="login.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> login(HttpSession session, String username, String password) {
        ServerResponse<User> response = iUserService.login(username, password);
        if(response.isSuccess()) {
            User user = response.getData();
            if(user.getRole() != Const.Roles.ROLE_ADMIN) {
                return ServerResponse.createByErrorByMessage("您不是管理员，无法登录");
            }
            user.setPassword(StringUtils.EMPTY);
            session.setAttribute(Const.CURRENT_USER, user);
        }
        return response;
    }

}
