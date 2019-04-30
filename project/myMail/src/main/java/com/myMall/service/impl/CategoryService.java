package com.myMall.service.impl;

import com.myMall.common.ServerResponse;
import com.myMall.dao.CategoryMapper;
import com.myMall.pojo.Category;
import com.myMall.service.ICategoryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService implements ICategoryService {
    @Autowired
    CategoryMapper categoryMapper;

    public ServerResponse<Category> addCategory(String categoryName, Integer parentId) {
        if(StringUtils.isBlank(categoryName) || parentId == null) {
            return ServerResponse.createByErrorByMessage("参数传递错误");
        }
        Category cate = new Category();
        cate.setName(categoryName);
        cate.setParentId(parentId);
        cate.setStatus(true);
        int resultCount = categoryMapper.insert(cate);
        if(resultCount > 0) {
            return ServerResponse.createBySuccess();
        }
        return ServerResponse.createByErrorByMessage("添加失败");
    }

    public ServerResponse setCategoryName(String categoryName, Integer categoryId) {
        if(StringUtils.isBlank(categoryName) || categoryId == null) {
            return ServerResponse.createByErrorByMessage("参数传递错误");
        }
        Category cate = new Category();
        cate.setName(categoryName);
        cate.setParentId(categoryId);
        int resultCount = categoryMapper.updateByPrimaryKeySelective(cate);
        if(resultCount > 0) {
            return ServerResponse.createBySuccess();
        }
        return ServerResponse.createByErrorByMessage("修改失败");
    }
}
