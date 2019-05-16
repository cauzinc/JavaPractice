package com.sample.service;

import com.sample.common.ServerResponse;
import com.sample.dao.IBusinessDao;
import com.sample.dao.daoImplement.BusinessDaoImpl;
import com.sample.pojo.Business;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("businessService")
public class BusinessService {
    private IBusinessDao iBusinessDao;

    @Autowired
    public void setiBusinessDao(IBusinessDao iBusinessDao) {
        this.iBusinessDao = iBusinessDao;
    }

    public ServerResponse getBusinessList() {
        List<Business> businessList = iBusinessDao.getBusinessList();
        return ServerResponse.createBySuccess(businessList);
    }

    public ServerResponse getBusinessById(Integer businessId) {
        Business business = iBusinessDao.getBusinessById(businessId);
        if(business == null) {
            return ServerResponse.createByErrorMessage("取引先情報はありません");
        }
        return ServerResponse.createBySuccess(business);
    }
}
