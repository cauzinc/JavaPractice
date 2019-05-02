package com.myMall.controller.backend;

import com.myMall.common.ServerResponse;
import com.myMall.pojo.Product;
import com.myMall.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/admin/product/")
public class ProductManagerController {
    private IProductService iProductService;

    @Autowired
    ProductManagerController(IProductService iProductService) {
        this.iProductService = iProductService;
    }

    @RequestMapping(value = "save_product.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse saveProduct(Product product) {
        return iProductService.saveOrUpdateProduct(product);
    }

    public ServerResponse setStatus(int productId, @RequestParam(value = "status", defaultValue = "0") int status) {
        return iProductService.setStatus(productId, status);
    }


}
