package com.myMall.service;

import com.github.pagehelper.PageInfo;
import com.myMall.common.ServerResponse;
import com.myMall.pojo.Product;
import com.myMall.vo.ProductDetailVO;

public interface IProductService {
    ServerResponse saveOrUpdateProduct(Product product);

    ServerResponse setStatus(Integer productId, Integer status);

    ServerResponse<ProductDetailVO> getProductDetail(Integer productId);

    ServerResponse getProductList(int pageNum, int pageSize);

    ServerResponse searchProductList(int pageNum, int pageSize, Integer productId, String productName);

    ServerResponse<PageInfo> searchProductListForUser(Integer pageSize, Integer pageNum, String keyword, String sort, Integer categoryId);


}
