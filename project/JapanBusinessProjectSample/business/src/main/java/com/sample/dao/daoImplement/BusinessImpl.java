package com.sample.dao.daoImplement;

import com.sample.dao.IBusinessDao;
import com.sample.pojo.Business;
import com.sample.utils.DBUtil;
import com.sample.utils.DaoUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
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

    /**
     * insert new data
     * @param newItem
     */
    public void insertBusiness(Business newItem) throws Exception{
        Connection conn = DBUtil.getConn();
        String[] fields = {"businessName", "businessTel", "businessFax", "website", "address", "postcode", "advantageField",
                "bankName", "bankBranchName", "bankNumber", "bankRepresent", "stuffNum", "annualSales", "transactionDeadline",
                "paymentSight", "accountKind", "comment", "principalId", "status", "createUser", "updateUser", "createTime", "updateTime"};
        // generate sql sentence
        String[] values = new String[fields.length];

        for(int i = 0; i < fields.length; i ++) {
            values[i] = (!"createTime".equals(fields[i]) && !"updateTime".equals(fields[i])) ? "?" : "current_time()";
        }
        String sql ="INSERT INTO sample_business (" +
                String.join(",", fields) + ")" +
                "VALUES " +
                "(" + String.join(",", values) + ")";

        // execute
        PreparedStatement ptmt = conn.prepareStatement(sql);
        ptmt.setString(1, newItem.getBusinessName());
        ptmt.setString(2, newItem.getBusinessTel());
        ptmt.setString(3, newItem.getBusinessFax());
        ptmt.setString(4, newItem.getWebsite());
        ptmt.setString(5, newItem.getAddress());
        ptmt.setString(6, newItem.getPostcode());
        ptmt.setString(7, newItem.getAdvantageField());
        ptmt.setString(8, newItem.getBankName());
        ptmt.setString(9, newItem.getBankBranchName());
        ptmt.setString(10, newItem.getBankNumber());
        ptmt.setString(11, newItem.getBankRepresent());
        ptmt.setInt(12, newItem.getStuffNum());
        ptmt.setBigDecimal(13, newItem.getAnnualSales());
        ptmt.setDate(14, new java.sql.Date(newItem.getTransactionDeadline().getTime()));
        ptmt.setString(15, newItem.getPaymentSight());
        ptmt.setString(16, newItem.getAccountKind());
        ptmt.setString(17, newItem.getComment());
        ptmt.setInt(18, newItem.getPrincipalId());
        ptmt.setInt(19, 0);
        ptmt.setString(20, "admin");
        ptmt.setString(21, "admin");
        ptmt.execute();
    }


    public void deleteBusinessById(Integer id) throws Exception{
        Connection conn = DBUtil.getConn();
        String sql = "DELETE FROM sample_business WHERE id=?";
        PreparedStatement ptmt = conn.prepareStatement(sql);
        ptmt.setInt(1, id);
        ptmt.execute();
    }

    public Business getBusinessById(Integer id) throws Exception{
        Connection conn = DBUtil.getConn();
        String sql = "SELECT * FROM sample_business WHERE id=?";
        PreparedStatement ptmt = conn.prepareStatement(sql);
        ptmt.setInt(1, id);
        // handle result
        ResultSet rs = ptmt.executeQuery();
        Business business = new Business();
        while(rs.next()) {
            business = (Business) DaoUtils.fillObject(rs, Business.class);
        }
        return business;
    }

    public void updateBusiness(Business business) throws Exception{
        Connection conn = DBUtil.getConn();
        String[] updateFields = {"businessName=?", "businessTel=?", "businessFax=?", "website=?", "address=?", "postcode=?", "advantageField=?",
                "bankName=?", "bankBranchName=?", "bankNumber=?", "bankRepresent=?", "stuffNum=?", "annualSales=?", "transactionDeadline=?",
                "paymentSight=?", "accountKind=?", "comment=?", "principalId=?", "status=?", "updateUser=?", "updateTime=current_time()"};
        String sql = "UPDATE sample_business SET" +
                "(" + String.join(",", updateFields) + ")" +
                "where id = ?";
        PreparedStatement ptmt = conn.prepareStatement(sql);

        ptmt.setString(1, business.getBusinessName());
        ptmt.setString(2, business.getBusinessTel());
        ptmt.setString(3, business.getBusinessFax());
        ptmt.setString(4, business.getWebsite());
        ptmt.setString(5, business.getAddress());
        ptmt.setString(6, business.getPostcode());
        ptmt.setString(7, business.getAdvantageField());
        ptmt.setString(8, business.getBankName());
        ptmt.setString(9, business.getBankBranchName());
        ptmt.setString(10, business.getBankNumber());
        ptmt.setString(11, business.getBankRepresent());
        ptmt.setInt(12, business.getStuffNum());
        ptmt.setBigDecimal(13, business.getAnnualSales());
        ptmt.setDate(14, new java.sql.Date(business.getTransactionDeadline().getTime()));
        ptmt.setString(15, business.getPaymentSight());
        ptmt.setString(16, business.getAccountKind());
        ptmt.setString(17, business.getComment());
        ptmt.setInt(18, business.getPrincipalId());
        ptmt.setInt(19, business.getStatus());
        ptmt.setString(20, "admin");
        ptmt.setInt(21, business.getId());
        ptmt.execute();
    }
}
