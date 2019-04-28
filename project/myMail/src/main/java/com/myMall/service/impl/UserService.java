package com.myMall.service.impl;

import com.myMall.common.ServerResponse;
import com.myMall.dao.UserMapper;
import com.myMall.pojo.User;
import com.myMall.service.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// 这里设定了bean的id，表示自动注入时，要使用对应的字段名称
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

        // todo 密码MD5加密

        User user = userMapper.selectLogin(username, password);
        if(user == null) {
            return ServerResponse.createByErrorByMessage("密码错误");
        }
        // 隐藏用户密码，但是为什么返回这个？？？
        user.setPassword(StringUtils.EMPTY);
        return ServerResponse.createBySuccess("登录成功", user);
    }
}
