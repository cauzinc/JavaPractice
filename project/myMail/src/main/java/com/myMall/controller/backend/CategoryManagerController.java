package com.myMall.controller.backend;

import com.myMall.common.Const;
import com.myMall.common.ServerResponse;
import com.myMall.pojo.User;
import com.myMall.service.ICategoryService;
import com.myMall.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/manager/category/")
public class CategoryManagerController {
    @Autowired
    private IUserService iUserService;

    @Autowired
    private ICategoryService iCategoryService;

    // todo 权限检验操作用AOP进行改造

    /**
     * 增加一个新的分类，给parentId设置默认值0，如果前端没有传则使用这个默认值
     * @param session
     * @param categoryName
     * @param parentId
     * @return
     */
    @RequestMapping(value = "add_category.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse addCategory(HttpSession session, String categoryName, @RequestParam(value = "parentId", defaultValue = "0") int parentId) {
        // 检测用户身份
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null) {
            return ServerResponse.createByErrorByMessage("用户未登录");
        }
        ServerResponse response = iUserService.checkAdmin(user.getId());
        if(!response.isSuccess()) {
            return ServerResponse.createByErrorByMessage("您没有权限");
        }
        return iCategoryService.addCategory(categoryName, parentId);
    }

    @RequestMapping(value = "set_category_name.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse setCategoryName(HttpSession session, String categoryName, int categoryId) {
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null) {
            return ServerResponse.createByErrorByMessage("用户未登录");
        }
        ServerResponse response = iUserService.checkAdmin(user.getId());
        if(!response.isSuccess()) {
            return ServerResponse.createByErrorByMessage("您没有权限");
        }
        return iCategoryService.setCategoryName(categoryName, categoryId);
    }

}
