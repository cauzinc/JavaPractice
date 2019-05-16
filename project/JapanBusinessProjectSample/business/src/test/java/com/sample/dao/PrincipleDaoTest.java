package com.sample.dao;

import com.sample.dao.daoImplement.PrincipleDaoImpl;
import com.sample.pojo.Principle;
import com.sample.vo.PrincipleListItem;
import org.junit.Test;

import java.util.List;

public class PrincipleDaoTest {
    private static IPrincipleDao principleDao = new PrincipleDaoImpl();

    @Test
    public void testGetPrincipleList() {

        List<PrincipleListItem> principleList = principleDao.getPrincipleRelatedList();

        for(PrincipleListItem business : principleList) {
            System.out.printf("principleName: %s, businessName: %s, tel: %s \n",
                    business.getPrincipleName(), business.getBusinessName(), business.getTel());
        }
    }

    @Test
    public void testGetPrincipleDetail() {
        Principle principle = principleDao.getPrincipleById(20);
        System.out.printf("name: %s, tel: %s, address: %s \n",
                principle.getPrincipleName(), principle.getTel(), principle.getAddress());
    }

    @Test
    public void testInsertPrinciple() {
        Principle principle = new Principle();

        principle.setPrincipleName("Mary");
        principle.setAddress("Osa");
        principle.setComment(null);
        principle.setCreateUser("admin");
        principle.setUpdateUser("admin");
        principle.setDepartment("Sell");
        principle.setFax("777-890");
        principle.setMail("mary@gmail.com");
        principle.setPhone("176000987");
        principle.setFavorite("tennis");
        principle.setPosition("se");
        principle.setTel("1213131");

        principleDao.insertPrinciple(principle);
    }

    @Test
    public void testUpdatePrinciple() {
        Principle principle = principleDao.getPrincipleById(20);
        principle.setMail("cauzinc@163.com");
        principleDao.updatePrinciple(principle);
    }

    @Test
    public void deletePrinciple() {
        principleDao.deletePrinciple(22);
    }
}
