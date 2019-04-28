package com.myMall.service;

import com.myMall.common.ServerResponse;
import com.myMall.pojo.User;

public interface IUserService {
    ServerResponse<User> login(String username, String password);
}
