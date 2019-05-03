package com.myMall.controller.portal;

import com.myMall.common.ServerResponse;
import com.myMall.service.IProductService;
import com.myMall.vo.ProductDetailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/product/")
public class ProductController {
    private IProductService iProductService;
    @Autowired
    public ProductController(IProductService iProductService) {
        this.iProductService = iProductService;
    }

    @RequestMapping(value = "get_product_detail.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<ProductDetailVO> getProductDetail(int productId) {
        return iProductService.getProductDetail(productId);
    }

    @RequestMapping(value = "search_product_list.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse searchProductList(
            @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
            @RequestParam(value = "pageSize", defaultValue = "5") int pageSize,
            @RequestParam(value = "categoryId", required = false) Integer categoryId,
            @RequestParam(value = "pageNum", required = false) String keyword,
            @RequestParam(value = "pageNum", defaultValue = "") String sort) {
        return iProductService.searchProductListForUser(pageSize, pageNum, keyword, sort, categoryId);
    }
}
