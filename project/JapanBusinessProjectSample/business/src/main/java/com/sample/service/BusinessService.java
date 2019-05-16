package com.sample.service;

import com.sample.common.ServerResponse;
import com.sample.dao.IBusinessDao;
import com.sample.dao.daoImplement.BusinessDaoImpl;
import com.sample.pojo.Business;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("businessService")
public class BusinessService {
    private IBusinessDao iBusinessDao = new BusinessDaoImpl();

    public ServerResponse getBusinessList() {
        List<Business> businessList = iBusinessDao.getBusinessList();
        return ServerResponse.createBySuccess(businessList);
    }
}
