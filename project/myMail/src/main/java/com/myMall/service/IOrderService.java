package com.myMall.service;

import com.myMall.common.ServerResponse;

import java.util.Map;

public interface IOrderService {
    ServerResponse pay(Integer userId, Long orderNo, String path);

    ServerResponse aliCallback(Map<String, String> params);
}
