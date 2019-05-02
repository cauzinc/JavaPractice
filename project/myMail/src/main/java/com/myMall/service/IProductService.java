package com.myMall.service;

import com.myMall.common.ServerResponse;
import com.myMall.pojo.Product;
import com.myMall.vo.ProductDetailVO;

public interface IProductService {
    ServerResponse saveOrUpdateProduct(Product product);

    ServerResponse setStatus(Integer productId, Integer status);

    ServerResponse<ProductDetailVO> getProductDetail(Integer productId);

    ServerResponse getProductList(int pageNum, int pageSize);

    ServerResponse searchProductList(int pageNum, int pageSize, int productId, String productName);
}
