package com.myMall.service;

import com.myMall.common.ServerResponse;
import com.myMall.pojo.User;

public interface IUserService {
    ServerResponse<User> login(String username, String password);

    ServerResponse<String> register(User user);

    ServerResponse<String> checkValid(String value, String type);

    ServerResponse<String> selectQuestion(String username);

    ServerResponse<String> checkForgetAnswer(String username, String question, String answer);

    ServerResponse<String> resetPasswordByToken(String username, String newPassword, String token);

    ServerResponse<String> resetPassword(User user, String oldPassword, String newPassword);
}
