package com.myMall.service.impl;

import com.myMall.common.ResponseCode;
import com.myMall.common.ServerResponse;
import com.myMall.dao.ProductMapper;
import com.myMall.pojo.Product;
import com.myMall.service.IProductService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("iProductService")
public class ProductService implements IProductService {
    @Autowired
    ProductService(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    private ProductMapper productMapper;

    public ServerResponse saveOrUpdateProduct(Product product) {
        if(product == null) {
            return ServerResponse.createByErrorByErrorCode(ResponseCode.ILLEGAL_ARGUMENT.getCode(), "参数错误");
        }
        // 获取mainImage
        if(!StringUtils.isBlank(product.getSubImages())) {
            String[] subImages = product.getSubImages().split(",");
            product.setMainImage(subImages[0]);
        }
        int resultCount = product.getId() != null ? productMapper.updateByPrimaryKeySelective(product) : productMapper.insert(product);
        if(resultCount > 0) {
            return ServerResponse.createBySuccessByMessage("更新成功");
        }
        return ServerResponse.createByErrorByMessage("数据储存失败");
    }

    public ServerResponse setStatus(Integer productId, Integer status) {
        if(productId == null || status == null) {
            return ServerResponse.createByErrorByErrorCode(ResponseCode.ILLEGAL_ARGUMENT.getCode(), "参数错误");
        }
        Product product = new Product();
        product.setId(productId);
        product.setStatus(status);
        if(productMapper.updateByPrimaryKeySelective(product) > 0) {
            return ServerResponse.createBySuccessByMessage("更新成功");
        }
        return ServerResponse.createByErrorByMessage("数据储存失败");
    }
}
