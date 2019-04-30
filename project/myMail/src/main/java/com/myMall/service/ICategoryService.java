package com.myMall.service;

import com.myMall.common.ServerResponse;
import com.myMall.pojo.Category;

import java.util.List;

public interface ICategoryService {

    ServerResponse addCategory(String categoryName, Integer parentId);

    ServerResponse setCategoryName(String categoryName, Integer categoryId);

    ServerResponse<List<Category>> getParallelChildrenCategory(Integer parentId);

    ServerResponse<List<Category>> getDeepChildrenCategory(Integer parentId);
}
