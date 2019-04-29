package com.myMall.service.impl;

import com.myMall.common.Const;
import com.myMall.common.ServerResponse;
import com.myMall.common.TokenCache;
import com.myMall.dao.UserMapper;
import com.myMall.pojo.User;
import com.myMall.service.IUserService;
import com.myMall.util.MD5Util;
import net.sf.jsqlparser.schema.Server;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

// 这里设定了bean的id，表示自动注入时，注入和id一致的bean
@Service("iUserService")
public class UserService implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public ServerResponse<User> login(String username, String password) {
        int resultCount = userMapper.checkUsername(username);
        if(resultCount == 0) {
            return ServerResponse.createByErrorByMessage("用户不存在");
        }

        // 密码要md5加密
        User user = userMapper.selectLogin(username, MD5Util.MD5EncodeUtf8(password));
        if(user == null) {
            return ServerResponse.createByErrorByMessage("密码错误");
        }
        // 隐藏用户密码
        user.setPassword(StringUtils.EMPTY);
        return ServerResponse.createBySuccess("登录成功", user);
    }

    @Override
    public ServerResponse<String> register(User user) {
        // 校验用户名和邮箱是否已存在
        ServerResponse<String> response = checkValid(user.getUsername(), Const.USERNAME);
        if(!response.isSuccess()) {
            return response;
        }
        response = checkValid(user.getEmail(), Const.EMAIL);
        if(!response.isSuccess()) {
            return response;
        }

        user.setRole(Const.Roles.ROLE_CUSTOMER);
        // 为密码设置MD5加密
        user.setPassword(MD5Util.MD5EncodeUtf8(user.getPassword()));
        // 这里要检查是否成功插入了数据
        if(userMapper.insert(user) == 0) {
            return ServerResponse.createByErrorByMessage("注册失败");
        }
        return ServerResponse.createBySuccessByMessage("注册成功");
    }

    /**
     * 检验数据是否合法
     * @param value
     * @param type
     * @return
     */
    public ServerResponse<String> checkValid(String value, String type) {
        if(!StringUtils.isBlank(value)) {
            if(Const.USERNAME.equals(type) && userMapper.checkUsername(value) > 0) {
                return ServerResponse.createByErrorByMessage("用户名已存在");
            }
            if(Const.EMAIL.equals(type) && userMapper.checkUserEmail(value) > 0) {
                return ServerResponse.createByErrorByMessage("用户邮箱已存在");
            }

        } else {
            return ServerResponse.createByErrorByMessage("参数错误");
        }
        // 可以用isSuccess()来验证response的boolean
        return ServerResponse.createBySuccessByMessage("校验成功");
    }

    public ServerResponse<String> selectQuestion(String username) {
        boolean ifUserNotExist = checkValid(username, Const.USERNAME).isSuccess();
        if(ifUserNotExist) {
            return ServerResponse.createByErrorByMessage("用户不存在");
        }
        String question = userMapper.selectQuestionByUsername(username);
        if(StringUtils.isNotBlank(question)) {
            return ServerResponse.createBySuccess(question);
        }
        return ServerResponse.createByErrorByMessage("用户未设置问题");
    }

    public ServerResponse<String> checkForgetAnswer(String username, String question, String answer) {
        int resultCount = userMapper.checkUserQuestion(username, question, answer);
        if(resultCount == 0) {
            return ServerResponse.createByErrorByMessage("问题答案不正确");
        }
        // 用java.util.UUID类来生成一个唯一不可重复的token, 并将token保存到本地缓存中
        String token = java.util.UUID.randomUUID().toString();
        TokenCache.setKey("token_" + username, token);
        return ServerResponse.createBySuccessByMessage(token);
    }

}
