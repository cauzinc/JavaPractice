package com.myMall.service.impl;

import com.myMall.common.ServerResponse;
import com.myMall.dao.CategoryMapper;
import com.myMall.pojo.Category;
import com.myMall.service.ICategoryService;
import net.sf.jsqlparser.schema.Server;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService implements ICategoryService {

    private CategoryMapper categoryMapper;
    private Logger logger = LoggerFactory.getLogger(CategoryService.class);

    @Autowired
    CategoryService(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

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



    public ServerResponse<List<Category>> getParallelChildrenCategory(Integer parentId) {
        List<Category> categoryList = categoryMapper.getParallelChildrenCategory(parentId);
        if(CollectionUtils.isEmpty(categoryList)) {
            logger.debug("没有找到子目录");
            return ServerResponse.createByErrorByMessage("没有找到子目录");
        }
        return ServerResponse.createBySuccess(categoryList);
    }

    public ServerResponse<List<Category>> getDeepChildrenCategory(Integer parentId) {
        List<Category> categoryList = categoryMapper.getParallelChildrenCategory(parentId);
        if(CollectionUtils.isEmpty(categoryList)) {
            logger.debug("没有找到子目录");
            return ServerResponse.createByErrorByMessage("没有找到子目录");
        }
        categoryList.addAll(deepSearch(categoryList));
        return ServerResponse.createBySuccess(categoryList);
    }

    private List<Category> deepSearch(List<Category> categoryList) {
        List<Category> resultSet = new ArrayList<>();
        for(Category cate : categoryList) {
            List<Category> child = categoryMapper.getParallelChildrenCategory(cate.getId());
            if(!CollectionUtils.isEmpty(child)) {
                resultSet.addAll(child);
                resultSet.addAll(deepSearch(child));
            }
        }
        return resultSet;
    }

}
