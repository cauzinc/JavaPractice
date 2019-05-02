package com.myMall.dao;

import com.myMall.pojo.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);

    // pageHelper 自动注入limit 和 offset 属性进行分页
    List<Product> getProductList();

    List<Product> searchProductList(@Param(value = "productId") Integer productId, @Param(value = "productName") String productName);
}