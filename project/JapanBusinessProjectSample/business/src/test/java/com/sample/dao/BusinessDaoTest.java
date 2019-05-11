package com.sample.dao;


import com.sample.dao.daoImplement.BusinessImpl;
import com.sample.pojo.Business;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


public class BusinessDaoTest {
    private static IBusinessDao businessDao = new BusinessImpl();

    @Test
    public void testGetBusinessList() {

        List<Business> businessList = businessDao.getBusinessList();

        for(Business business : businessList) {
            System.out.printf("name: %s, tel: %s \n", business.getBusinessName(), business.getBusinessTel());
        }
    }

    @Test
    public void testGetBusinessDetail() {
        Business business = businessDao.getBusinessById(1);
        System.out.printf("name: %s, tel: %s, address: %s \n",
                business.getBusinessName(), business.getBusinessTel(), business.getAddress());
    }

    @Test
    public void testQuickInsert() {
        Business business = new Business();
        business.setBusinessName("Company");
        business.setBusinessTel("123333");
        business.setWebsite("company.com");
        businessDao.quickInsert(business);
    }

    @Test
    public void testInsertBusiness() {
        Business business = new Business();

        business.setBusinessName("Company");
        business.setBusinessFax("333-111");
        business.setBusinessTel("19037226721");
        business.setWebsite("company.com");
        business.setAddress("KamiNaGawa");
        business.setAnnualSales(new BigDecimal("3000.11"));
        business.setBankName("Japan Bank");
        business.setBankNumber("334");
        business.setBankBranchName(null);
        business.setBankRepresent("Serena");
        business.setComment(null);
        business.setPostcode("000010");
        business.setAdvantageField("ball");
        business.setAccountKind("normal");
        business.setStuffNum(200);
        business.setPrincipalId(20);
        business.setTransactionDeadline(new Date());

        businessDao.insertBusiness(business);
    }

    @Test
    public void testUpdateBusiness() {
        Business business = businessDao.getBusinessById(5);

        business.setStatus(1);

        businessDao.updateBusiness(business);
    }


}
