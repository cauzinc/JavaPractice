package com.myMall.controller.backend;

import com.myMall.common.ServerResponse;
import com.myMall.service.ICategoryService;
import com.myMall.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/admin/category/")
public class CategoryManagerController {
    @Autowired
    private IUserService iUserService;

    @Autowired
    private ICategoryService iCategoryService;

    // todo 权限检验操作用AOP进行改造

    /**
     * 增加一个新的分类，给parentId设置默认值0，如果前端没有传则使用这个默认值
     * @param categoryName
     * @param parentId
     * @return
     */
    @RequestMapping(value = "add_category.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse addCategory(String categoryName, @RequestParam(value = "parentId", defaultValue = "0") int parentId) {
        return iCategoryService.addCategory(categoryName, parentId);
    }

    @RequestMapping(value = "set_category_name.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse setCategoryName(String categoryName, int categoryId) {
        return iCategoryService.setCategoryName(categoryName, categoryId);
    }

    @RequestMapping(value = "get_category.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse getCategory(@RequestParam(value="parentId", defaultValue = "0") int parentId,
                                      @RequestParam(value="deep", defaultValue = "0") int deep) {
        boolean ifDeep = deep == 1;
        return iCategoryService.getCategory(parentId, ifDeep);
    }

}
