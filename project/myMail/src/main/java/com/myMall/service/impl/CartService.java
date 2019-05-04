package com.myMall.service.impl;

import com.google.common.collect.Lists;
import com.myMall.common.Const;
import com.myMall.common.ResponseCode;
import com.myMall.common.ServerResponse;
import com.myMall.dao.CartMapper;
import com.myMall.dao.ProductMapper;
import com.myMall.pojo.Cart;
import com.myMall.pojo.Product;
import com.myMall.service.ICartService;
import com.myMall.util.BigDecimalUtil;
import com.myMall.util.PropertiesUtil;
import com.myMall.vo.CartProductVo;
import com.myMall.vo.CartVo;
import net.sf.jsqlparser.schema.Server;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service("iCartService")
public class CartService implements ICartService {
    private CartMapper cartMapper;
    private ProductMapper productMapper;

    @Autowired
    public void setCartMapper(CartMapper cartMapper) {
        this.cartMapper = cartMapper;
    }
    @Autowired
    public void setProductMapper(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    // 添加到购物车
    public ServerResponse<CartVo> add(Integer userId, Integer productId, Integer count) {
        // 检查参数
        if(productId == null) {
            return ServerResponse.createByErrorByErrorCode(ResponseCode.ILLEGAL_ARGUMENT.getCode(), "参数错误");
        }
        // 判断用户是否已经添加过该产品
        Cart cart = cartMapper.getUserCartItem(productId, userId);
        if(cart == null) {
            Cart newItem = new Cart();
            newItem.setUserId(userId);
            newItem.setProductId(productId);
            newItem.setChecked(0);
            newItem.setQuantity(count);
            cartMapper.insert(newItem);
        } else {
            count += cart.getQuantity();
            cart.setQuantity(count);
            cartMapper.updateByPrimaryKeySelective(cart);
        }
        // 返回用户的购物车cartVO对象
        CartVo userCartVO = getCartVOByUserId(userId);
        return ServerResponse.createBySuccess(userCartVO);
    }

    // 修改购物车数量
    public ServerResponse<CartVo> update(Integer userId, Integer productId, Integer count) {
        // 检查参数
        if(productId == null) {
            return ServerResponse.createByErrorByErrorCode(ResponseCode.ILLEGAL_ARGUMENT.getCode(), "参数错误");
        }
        // 更新数量
        Cart updateItem = cartMapper.getUserCartItem(userId, productId);
        if(updateItem != null) {
            updateItem.setQuantity(count + updateItem.getQuantity());
        }
        cartMapper.updateByPrimaryKeySelective(updateItem);

        // 返回cartVO
        CartVo result = getCartVOByUserId(userId);
        return ServerResponse.createBySuccess(result);
    }

    // 删除购物车中的商品
    public ServerResponse<CartVo> delete(Integer userId, Integer[] productIds) {
        // 检查参数
        List<Integer> productIdList = Arrays.asList(productIds);
        if(CollectionUtils.isEmpty(productIdList)) {
            return ServerResponse.createByErrorByErrorCode(ResponseCode.ILLEGAL_ARGUMENT.getCode(), "参数错误");
        }

        // 删除产品
        cartMapper.deleteProducts(userId, productIdList);

        // 返回cartVo
        CartVo result = getCartVOByUserId(userId);
        return ServerResponse.createBySuccess(result);
    }

    /**
     * 选中、取消选中； 全选、取消全选
     * @param userId
     * @param productId 如果没有传则表示全选或者取消全选
     * @param checked 选中或者取消选中状态
     * @return
     */
    public ServerResponse<CartVo> selectOrUnselect(Integer userId, Integer productId, Integer checked) {
        if(userId == null) {
            return ServerResponse.createByErrorByErrorCode(ResponseCode.ILLEGAL_ARGUMENT.getCode(), "参数错误");
        }
        cartMapper.selectOrUnselect(userId, productId, checked);
        CartVo result = getCartVOByUserId(userId);
        return ServerResponse.createBySuccess(result);
    }

    public ServerResponse<Integer> getProductCountInCart(Integer userId) {
        if(userId == null) {
            return ServerResponse.createByErrorByErrorCode(ResponseCode.ILLEGAL_ARGUMENT.getCode(), "参数错误");
        }
        Integer count = cartMapper.getProductCountInCart(userId);
        return ServerResponse.createBySuccess(count);
    }


    // 获取CartVO以及计算用户购物车中的货物是否有库存
    private CartVo getCartVOByUserId(Integer userId) {
        CartVo cartVo = new CartVo();
        List<CartProductVo> cartProductList = Lists.newArrayList();
        BigDecimal totalPrice = new BigDecimal("0");
        // todo 获取用户所有购物车里的内容
        List<Cart> cartList = cartMapper.getUserAllCartItems(userId);

        // todo 将cart对象转为cartProductVo, 并计算limit
        if(!CollectionUtils.isEmpty(cartList)) {
            for(Cart cartItem : cartList) {
                CartProductVo cartProductVo = assembleCartProductVo(cartItem, userId);
                // 如果已勾选，计算到总价中
                if(cartProductVo.getProductChecked() == Const.CartStatement.CHECKED) {
                    totalPrice = BigDecimalUtil.add(totalPrice.doubleValue(), cartProductVo.getProductTotalPrice().doubleValue());
                }
                cartProductList.add(cartProductVo);
            }
        }

        // todo 封装cartVO
        cartVo.setCartProductVoList(cartProductList);
        cartVo.setCartTotalPrice(totalPrice);
        cartVo.setImageHost(PropertiesUtil.getProperty("ftp.server.http.prefix"));
        cartVo.setAllChecked(checkIfAllChecked(userId));
        return cartVo;
    }

    private CartProductVo assembleCartProductVo(Cart cartItem, Integer userId) {
        CartProductVo cartProductVo = new CartProductVo();
        cartProductVo.setId(cartItem.getId());
        cartProductVo.setUserId(userId);
        cartProductVo.setProductId(cartItem.getProductId());
        // 获取对应的product数据
        Product product = productMapper.selectByPrimaryKey(cartItem.getProductId());
        if(product != null) {
            cartProductVo.setProductMainImage(product.getMainImage());
            cartProductVo.setProductName(product.getName());
            cartProductVo.setProductSubtitle(product.getSubtitle());
            cartProductVo.setProductStatus(product.getStatus());
            cartProductVo.setProductPrice(product.getPrice());
            cartProductVo.setProductStock(product.getStock());
            // 判断库存
            int limit = 0;
            if(product.getStock() >= cartItem.getQuantity()) {
                limit = cartItem.getQuantity();
                cartProductVo.setLimitQuantity(Const.CartStatement.LIMIT_NUM_SUCCESS);
            } else {
                limit = product.getStock();
                cartProductVo.setLimitQuantity(Const.CartStatement.LIMIT_NUM_FAIL);
                // 直接更新购物车中的数额
                Cart updateCart = new Cart();
                updateCart.setId(cartItem.getId());
                updateCart.setQuantity(limit);
                cartMapper.updateByPrimaryKeySelective(updateCart);
            }
            cartProductVo.setQuantity(limit);
            cartProductVo.setProductTotalPrice(BigDecimalUtil.multi(product.getPrice().doubleValue(), cartItem.getQuantity().doubleValue()));
            cartProductVo.setProductChecked(cartItem.getChecked());
        }
        return cartProductVo;
    }

    private boolean checkIfAllChecked(Integer userId) {
        return userId == null && cartMapper.checkUserAllChecked(userId) == 0;
    }
}
