package com.myMall.controller.backend;

import com.myMall.common.ServerResponse;
import com.myMall.pojo.Product;
import com.myMall.service.IProductService;
import com.myMall.vo.ProductDetailVO;
import net.sf.jsqlparser.schema.Server;
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

    @RequestMapping(value = "set_status.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse setStatus(int productId, @RequestParam(value = "status", defaultValue = "0") int status) {
        return iProductService.setStatus(productId, status);
    }

    @RequestMapping(value = "get_product_detail.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<ProductDetailVO> getProductDetail(int productId) {
        return iProductService.getProductDetail(productId);
    }

    @RequestMapping(value = "get_product_list.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse getProductList(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum, int pageSize) {
        return iProductService.getProductList(pageNum, pageSize);
    }

    @RequestMapping(value = "search_product_list.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse searchProductList(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum, int pageSize, Integer productId, String productName) {
        return iProductService.searchProductList(pageNum, pageSize, productId, productName);
    }

}
