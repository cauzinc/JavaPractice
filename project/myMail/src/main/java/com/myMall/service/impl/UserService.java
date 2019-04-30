package com.myMall.service.impl;

import com.github.pagehelper.StringUtil;
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
        if(StringUtils.isNotBlank(value) && StringUtils.isNotBlank(type)) {
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
        TokenCache.setKey(TokenCache.TOKEN_PREFIX + username, token);
        return ServerResponse.createBySuccess(token);
    }

    /**
     * 通过问题重置密码
     * @param username
     * @param newPassword
     * @param token 回答问题时获取的token
     * @return
     */
    public ServerResponse<String> resetPasswordByToken(String username, String newPassword, String token) {
        if(StringUtils.isBlank(token)) {
            return ServerResponse.createByErrorByMessage("缺少token");
        }
        int resultCount = userMapper.checkUsername(username);
        if(resultCount == 0) {
            return ServerResponse.createByErrorByMessage("用户不存在");
        }
        String tokenInCache = TokenCache.getValue(TokenCache.TOKEN_PREFIX + username);
        if(StringUtils.isBlank(tokenInCache)) {
            return ServerResponse.createByErrorByMessage("token已过期");
        }

        if(StringUtils.equals(token, tokenInCache)) {
            // 保存MD5加密后的密码
            String md5Password = MD5Util.MD5EncodeUtf8(newPassword);
            int updateCount = userMapper.setUserPassword(username, md5Password);
            if(updateCount > 0) {
                return ServerResponse.createBySuccessByMessage("修改密码成功");
            }
        } else {
            return ServerResponse.createByErrorByMessage("token错误，请重新获取token");
        }

        // token不一致
        return ServerResponse.createByErrorByMessage("密码修改失败");
    }

    /**
     * 登录用户重置密码
     * @param user
     * @param oldPassword
     * @param newPassword
     * @return
     */
    public ServerResponse<String> resetPassword(User user, String oldPassword, String newPassword) {
        int userId = user.getId();
        int resultCount = userMapper.checkUserPassword(userId, MD5Util.MD5EncodeUtf8(oldPassword));
        if(resultCount == 0) {
            return ServerResponse.createByErrorByMessage("密码错误，修改密码失败");
        }
        String md5Password = MD5Util.MD5EncodeUtf8(newPassword);
        user.setPassword(md5Password);
        int updateCount = userMapper.updateByPrimaryKeySelective(user);
        if(updateCount > 0) {
            return ServerResponse.createBySuccessByMessage("修改密码成功");
        }
        return ServerResponse.createBySuccessByMessage("修改密码失败");
    }

    /**
     * 更新用户信息，用户名不可以被修改
     * 要检查新的邮箱名是否和其他用户的邮箱名重复
     * @param updateUser
     * @return
     */
    public ServerResponse<User> updateUserInfo(User updateUser) {
        String email = updateUser.getEmail();
        int userId = updateUser.getId();
        int resultCount = userMapper.checkEmailUsed(email, userId);
        if(resultCount > 0) {
            return ServerResponse.createByErrorByMessage("改邮箱已被使用");
        }
        User userCopy = new User();
        // 只更新下列数据, id 和 username 返回到controller以储存到session
        userCopy.setId(updateUser.getId());
        userCopy.setUsername(updateUser.getUsername());
        userCopy.setEmail(updateUser.getEmail());
        userCopy.setPhone(updateUser.getPhone());
        userCopy.setQuestion(updateUser.getQuestion());
        userCopy.setAnswer(updateUser.getAnswer());
        int updateCount = userMapper.updateByPrimaryKeySelective(userCopy);
        if(updateCount > 0) {
            return ServerResponse.createBySuccess("更新成功", userCopy);
        }
        return ServerResponse.createByErrorByMessage("更新失败");
    }

    public ServerResponse<User> getUserInfoById(int userId) {
        User user = userMapper.selectByPrimaryKey(userId);
        if(user == null) {
            return ServerResponse.createBySuccessByMessage("找不到该用户的信息");
        }
        // 要记住隐藏用户的密码信息
        user.setPassword(StringUtils.EMPTY);
        return ServerResponse.createBySuccess(user);
    }

    public ServerResponse checkAdmin(int userId) {
        int resultCount = userMapper.checkAdmin(userId);
        if(resultCount > 0) {
            return ServerResponse.createBySuccess();
        }
        return ServerResponse.createByError();
    }
}
