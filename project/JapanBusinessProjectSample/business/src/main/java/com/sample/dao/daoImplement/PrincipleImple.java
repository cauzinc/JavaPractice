package com.sample.dao.daoImplement;

import com.sample.dao.IPrincipleDao;
import com.sample.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class PrincipleImple implements IPrincipleDao {
    public void getPrincipleRelatedList() {
        Connection conn = DBUtil.getConn();
        String sql = "SELECT principle_name, business_name, tel " +
                "FROM sample_business, sample_principle " +
                "WHERE sample_business.principal_id = sample_principle.id";

        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void insertPrinciple() {

    }

    public void updatePrinciple() {

    }

    public void deletePrinciple() {

    }

    public void getPrincipleById() {

    }



}
