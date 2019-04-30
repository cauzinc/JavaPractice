package com.myMall.service;

import com.myMall.common.ServerResponse;

public interface ICategoryService {

    ServerResponse addCategory(String categoryName, Integer parentId);

    ServerResponse setCategoryName(String categoryName, Integer categoryId);
}
