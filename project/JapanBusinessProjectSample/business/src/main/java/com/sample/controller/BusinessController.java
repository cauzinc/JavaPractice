package com.sample.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.sample.common.ResponseCode;
import com.sample.common.ServerResponse;
import com.sample.dao.IBusinessDao;
import com.sample.dao.daoImplement.BusinessDaoImpl;
import com.sample.daoMapper.BusinessMapper;
import com.sample.pojo.Business;
import com.sample.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/business/")
public class BusinessController {
    private IBusinessDao iBusinessDao;
    private BusinessService businessService;
    private BusinessMapper businessMapper;

    @Autowired
    public void setBusinessService(BusinessService businessService) {
        this.businessService = businessService;
    }
    @Autowired
    public void setiBusinessDao(IBusinessDao iBusinessDao) {
        this.iBusinessDao = iBusinessDao;
    }
    @Autowired
    public void setBusinessMapper(BusinessMapper businessMapper) {
        this.businessMapper = businessMapper;
    }

    @ResponseBody
    @RequestMapping(value = "getBusinessList", method = RequestMethod.GET)
    public ServerResponse getBusinessList() {
        return businessService.getBusinessList();
    }

    @ResponseBody
    @RequestMapping(value = "quickInsert", method = RequestMethod.POST)
    public ServerResponse quickInsert(@RequestBody Business business) {
        String name = business.getBusinessName();
        if(name == null || "".equals(name.trim())) {
            return ServerResponse.createByErrorMessage("引取先名は必要です｡");
        }
        iBusinessDao.quickInsert(business);
        return ServerResponse.createBySuccess(business);
    }

    @ResponseBody
    @RequestMapping(value = "delete", method = RequestMethod.GET)
    public ServerResponse delete(int businessId) {
        iBusinessDao.deleteBusinessById(businessId);
        return ServerResponse.createBySuccessMessage("ok");
    }

    @ResponseBody
    @RequestMapping(value = "getBusinessById", method = RequestMethod.GET)
    public ServerResponse getBusinessById(Integer businessId) {
        if(businessId == null) {
            return ServerResponse.createByErrorErrorCode(ResponseCode.ILLEGAL_ARGUMENT.getCode(), "Need business id");
        }
        return businessService.getBusinessById(businessId);
    }

    @ResponseBody
    @RequestMapping(value = "insertBusiness", method = RequestMethod.POST)
    public ServerResponse insertBusiness(@RequestBody Business business) {
        if(business == null) {
            return ServerResponse.createByErrorErrorCode(ResponseCode.ILLEGAL_ARGUMENT.getCode(), "Parameter error");
        }
        return businessService.insertBusiness(business);
    }

    @ResponseBody
    @RequestMapping(value = "updateBusiness", method = RequestMethod.POST)
    public ServerResponse updateBusiness(@RequestBody Business business) {
        if(business == null) {
            return ServerResponse.createByErrorErrorCode(ResponseCode.ILLEGAL_ARGUMENT.getCode(), "Parameter error");
        }
        businessMapper.updateByPrimaryKeySelective(business);
        return ServerResponse.createBySuccessMessage("ok");
    }

}
