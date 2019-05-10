package com.myMall.service;

import com.myMall.common.ServerResponse;

public interface IOrderService {
    ServerResponse pay(Integer userId, Long orderNo, String path);
}
