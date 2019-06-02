package com.sample.service;

import com.sample.common.ServerResponse;
import com.sample.dao.IBusinessDao;
import com.sample.dao.daoImplement.BusinessDaoImpl;
import com.sample.daoMapper.BusinessMapper;
import com.sample.pojo.Business;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("businessService")
public class BusinessService {
    private IBusinessDao iBusinessDao;
    private BusinessMapper businessMapper;

    @Autowired
    public void setiBusinessDao(IBusinessDao iBusinessDao) {
        this.iBusinessDao = iBusinessDao;
    }
    @Autowired
    public void setBusinessMapper(BusinessMapper businessMapper) {
        this.businessMapper = businessMapper;
    }

    public ServerResponse getBusinessList() {
        List<Business> businessList = businessMapper.getBusinessList();
        return ServerResponse.createBySuccess(businessList);
    }

    public ServerResponse getBusinessById(Integer businessId) {
        Business business = iBusinessDao.getBusinessById(businessId);
        if(business == null) {
            return ServerResponse.createByErrorMessage("取引先情報はありません");
        }
        return ServerResponse.createBySuccess(business);
    }

    public ServerResponse insertBusiness(Business business) {
        business.setStatus(0);
        business.setId(null);
        if(businessMapper.insert(business) > 0) {
            return ServerResponse.createBySuccessMessage("ok");
        }
        return ServerResponse.createBySuccessMessage("保存失敗しました｡資料の入力をチェックしてください。");
    }

    public ServerResponse updateBusiness(Business business) {

        if(businessMapper.updateByPrimaryKeySelective(business) > 0) {
            return ServerResponse.createBySuccessMessage("ok");
        }
        return ServerResponse.createBySuccessMessage("保存失敗しました｡資料の入力をチェックしてください。");
    }


}
