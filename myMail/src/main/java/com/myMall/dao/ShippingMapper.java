package com.myMall.dao;

import com.myMall.pojo.Shipping;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShippingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Shipping record);

    int insertSelective(Shipping record);

    Shipping selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Shipping record);

    int updateByPrimaryKey(Shipping record);

    int deleteByUserId(@Param(value = "userId") Integer userId,
                       @Param(value = "shippingId") Integer shippingId);

    int updateByUserId(Shipping updateItem);

    Shipping selectByUserId(@Param(value = "userId") Integer userId,
                            @Param(value = "shippingId") Integer shippingId);

    List<Shipping> getUserShippingList(Integer userId);
}