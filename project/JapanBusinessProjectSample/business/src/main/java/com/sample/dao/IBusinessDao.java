package com.sample.dao;

import com.sample.pojo.Business;

import java.util.List;

public interface IBusinessDao {
    List<Business> getBusinessList();
    void quickInsert(Business newItem);
    void insertBusiness(Business newItem);
    void deleteBusinessById(Integer id);
    Business getBusinessById(Integer id);
    void updateBusiness(Business business);

}
