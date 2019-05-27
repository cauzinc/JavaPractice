package com.myMall.service;

import com.myMall.common.ServerResponse;
import com.myMall.pojo.Shipping;

public interface IShippingService {
    ServerResponse add(Integer userId, Shipping shipping);
    ServerResponse delete(Integer userId, Integer shippingId);
    ServerResponse update(Integer userId, Shipping updateItem);
    ServerResponse select(Integer userId, Integer shippingId);
    ServerResponse selectAll(int pageNum, int pageSize, Integer userId);
}
