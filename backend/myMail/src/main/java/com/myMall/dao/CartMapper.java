package com.myMall.dao;

import com.myMall.pojo.Cart;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CartMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Cart record);

    int insertSelective(Cart record);

    Cart selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Cart record);

    int updateByPrimaryKey(Cart record);

    Cart getUserCartItem(@Param(value="productId") Integer productId,
                         @Param(value="userId")Integer userId);

    List<Cart> getUserAllCartItems(Integer userId);

    List<Cart> selectCheckedListByUserId(Integer userId);

    int checkUserAllChecked(Integer userId);

    int deleteProducts(@Param(value="userId") Integer userId,
                       @Param(value="productIds") List<Integer> productIds);

    int selectOrUnselect(@Param(value="userId") Integer userId,
                         @Param(value="productId") Integer productId,
                         @Param(value="checked") Integer checked);

    int getProductCountInCart(@Param(value="userId") Integer userId);
}