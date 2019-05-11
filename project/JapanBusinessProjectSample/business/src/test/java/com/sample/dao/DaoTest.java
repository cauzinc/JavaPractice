package com.sample.dao;


import com.sample.dao.daoImplement.BusinessImpl;
import com.sample.pojo.Business;
import org.junit.Test;

import java.util.List;


public class DaoTest {


    @Test
    public void testGetBusinessList() {
        IBusinessDao businessDao = new BusinessImpl();
        List<Business> businessList;
    }
}
