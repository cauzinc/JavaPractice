package com.sample.controller;

import com.sample.common.ServerResponse;
import com.sample.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/business/")
public class BusinessController {
    private BusinessService businessService;
    @Autowired
    public void setBusinessService(BusinessService businessService) {
        this.businessService = businessService;
    }

    @ResponseBody
    @RequestMapping(value = "getBusinessList", method = RequestMethod.GET)
    public ServerResponse getBusinessList() {
        return businessService.getBusinessList();
    }

}
