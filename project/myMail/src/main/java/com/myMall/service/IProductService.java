package com.myMall.service;

import com.myMall.common.ServerResponse;
import com.myMall.pojo.Product;

public interface IProductService {
    ServerResponse saveOrUpdateProduct(Product product);

    ServerResponse setStatus(Integer productId, Integer status);
}
