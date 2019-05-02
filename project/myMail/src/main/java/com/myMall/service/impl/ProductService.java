package com.myMall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.myMall.common.ResponseCode;
import com.myMall.common.ServerResponse;
import com.myMall.dao.CategoryMapper;
import com.myMall.dao.ProductMapper;
import com.myMall.pojo.Category;
import com.myMall.pojo.Product;
import com.myMall.service.IProductService;
import com.myMall.util.DateTimeUtil;
import com.myMall.util.PropertiesUtil;
import com.myMall.vo.ProductDetailVO;
import com.myMall.vo.ProductListVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service("iProductService")
public class ProductService implements IProductService {
    @Autowired
    ProductService(ProductMapper productMapper, CategoryMapper categoryMapper) {
        this.productMapper = productMapper;
        this.categoryMapper = categoryMapper;
    }

    private ProductMapper productMapper;
    private CategoryMapper categoryMapper;

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

    public ServerResponse<ProductDetailVO> getProductDetail(Integer productId) {
        if(productId == null) {
            return ServerResponse.createByErrorByErrorCode(ResponseCode.ILLEGAL_ARGUMENT.getCode(), "参数错误");
        }
        Product product = productMapper.selectByPrimaryKey(productId);
        if(product == null) {
            return ServerResponse.createByErrorByMessage("产品已下架或者删除");
        }
        ProductDetailVO productDetailVO = assembleWithProduct(product);
        return ServerResponse.createBySuccess(productDetailVO);
    }

    public ServerResponse getProductList(int pageNum, int pageSize) {
        /**
         * 1, startPage -- start
         * 2, 填充sql查询逻辑
         * 3, pageHelper 填充返回数据
         */
        PageHelper.startPage(pageNum, pageSize);
        List<Product> productList = productMapper.getProductList();
        List<ProductListVO> resultList = assembleListVO(productList);
        PageInfo<ProductListVO> pageResult = new PageInfo<>(resultList);
        return ServerResponse.createBySuccess(pageResult);
    }

    public ServerResponse searchProductList(int pageNum, int pageSize, Integer productId, String productName) {
        PageHelper.startPage(pageNum, pageSize);
        if(StringUtils.isNotBlank(productName)){
            productName = new StringBuilder().append("%").append(productName).append("%").toString();
        }
        List<Product> productList = productMapper.searchProductList(productId, productName);
        List<ProductListVO> resultList = assembleListVO(productList);
        PageInfo<ProductListVO> pageResult = new PageInfo<>(resultList);
        return ServerResponse.createBySuccess(pageResult);
    }

    // 装配productDetailVO
    private ProductDetailVO assembleWithProduct(Product product) {
        ProductDetailVO productDetailVo = new ProductDetailVO();
        productDetailVo.setId(product.getId());
        productDetailVo.setCategoryId(product.getCategoryId());
        productDetailVo.setName(product.getName());
        productDetailVo.setSubtitle(product.getSubtitle());
        productDetailVo.setMainImage(product.getMainImage());
        productDetailVo.setSubImages(product.getSubImages());
        productDetailVo.setDetail(product.getDetail());
        productDetailVo.setPrice(product.getPrice());
        productDetailVo.setStock(product.getStock());
        productDetailVo.setStatus(product.getStatus());

        Category category = categoryMapper.selectByPrimaryKey(product.getCategoryId());
        int parentCategoryId = category == null ? 0 : category.getParentId();
        productDetailVo.setParentCategoryId(parentCategoryId);

        productDetailVo.setImageHost(PropertiesUtil.getProperty("ftp.server.http.prefix", "http://img.happymmall.com/"));

        productDetailVo.setCreateTime(DateTimeUtil.dateToStr(product.getCreateTime()));
        productDetailVo.setUpdateTime(DateTimeUtil.dateToStr(product.getUpdateTime()));
        return productDetailVo;
    }

    // 装配productListVO
    private List<ProductListVO> assembleListVO(List<Product> productList) {
        List<ProductListVO> resultList = new ArrayList<>();
        for(Product p : productList) {
            ProductListVO listVO = new ProductListVO();
            listVO.setId(p.getId());
            listVO.setCategoryId(p.getCategoryId());
            listVO.setName(p.getName());
            listVO.setSubtitle(p.getSubtitle());
            listVO.setMainImage(p.getMainImage());
            listVO.setPrice(p.getPrice());
            listVO.setStatus(p.getStatus());
            listVO.setImageHost(PropertiesUtil.getProperty("ftp.server.http.prefix", "http://img.happymmall.com/"));
            resultList.add(listVO);
        }

        return resultList;
    }

}



