package com.myMall.controller.portal;

import com.myMall.common.Const;
import com.myMall.common.ServerResponse;
import com.myMall.pojo.User;
import com.myMall.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user/")
public class UserController {
    @Autowired
    private IUserService iUserService;

    /**
     * 用户登录
     * @param username
     * @param password
     * @param session
     * @return
     */
    @RequestMapping(value = "login.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> login(String username, String password, HttpSession session) {
        ServerResponse<User> response = iUserService.login(username, password);
        // 如果成功登录，则在session中保存用户的登录状态
        if(response.isSuccess()) {
            session.setAttribute(Const.CURRENT_USER, response.getData());
        }
        return response;
    }

    @RequestMapping(value = "logout.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<User> logout(HttpSession session) {
        session.removeAttribute(Const.CURRENT_USER);
        return ServerResponse.createBySuccess();
    }

    @RequestMapping(value = "register.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> register(User user) {
        return iUserService.register(user);
    }

    @RequestMapping(value = "check_valid.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<String> checkValid(String value, String type) {
        return iUserService.checkValid(value, type);
    }

    @RequestMapping(value = "get_user_info.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<User> getUserInfo(HttpSession session) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if(user != null) {
            return ServerResponse.createBySuccess(user);
        }
        return ServerResponse.createByErrorByMessage("用户未登录");
    }

    @RequestMapping(value = "get_forget_question.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<String> getForgetQuestion(String username) {
        return iUserService.selectQuestion(username);
    }

    @RequestMapping(value = "check_forget_question.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<String> checkForgetAnswer(String username, String question, String answer) {
        return iUserService.checkForgetAnswer(username, question, answer);
    }

    public ServerResponse<String> resetPasswordByToken(String username, String newPassword, String token) {
        return null;
    }

}
