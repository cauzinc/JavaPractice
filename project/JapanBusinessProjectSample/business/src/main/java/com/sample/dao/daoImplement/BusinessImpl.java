package com.sample.dao.daoImplement;

import com.sample.dao.IBusinessDao;
import com.sample.pojo.Business;
import com.sample.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BusinessImpl implements IBusinessDao {

    /**
     * get business list in portal page
     * @return
     * @throws Exception
     */
    public List<Business> getBusinessList() throws Exception{
        Connection conn = DBUtil.getConn();
        String sql = "SELECT business_name, address, business_tel FROM sample_business order by update_time";
        PreparedStatement ptmt = conn.prepareStatement(sql);
        ResultSet rs = ptmt.executeQuery();
        List<Business> result = new ArrayList<Business>();
        while(rs.next()) {
            Business item = new Business();
            item.setBusinessName(rs.getString("business_name"));
            item.setBusinessTel(rs.getString("business_tel"));
            item.setAddress(rs.getString("address"));
            result.add(item);
        }
        return result;
    }

    /**
     * insert business object with certain fields
     * @param newItem object with data inserted
     */
    public void quickInsert(Business newItem) throws Exception{
        Connection conn = DBUtil.getConn();
        String sql = "INSERT INTO sample_business (" +
                "business_name, business_tel, website, create_user, create_time, update_user, update_time) " +
                "VALUES (" +
                "? , ? , ?, 'admin', current_time(), 'admin', current_time())";
        PreparedStatement ptmt = conn.prepareStatement(sql);
        ptmt.setString(1, newItem.getBusinessName());
        ptmt.setString(2, newItem.getBusinessTel());
        ptmt.setString(3, newItem.getWebsite());
        ptmt.execute();
    }


    public void insertBusiness(Business newItem) {

    }


    public void deleteBusinessById(Integer id) {

    }

    public void getBusinessById(Integer id) {

    }

    public void updateBusiness(Business business) {

    }
}
