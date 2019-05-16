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

public class BusinessDaoImpl implements IBusinessDao {

    /**
     * get business list in portal page
     * @return
     * @throws Exception
     */
    public List<Business> getBusinessList() {
        Connection conn = DBUtil.getConn();
        String sql = "SELECT business_name, address, business_tel FROM sample_business order by update_time";
        List<Business> result = new ArrayList<Business>();
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ResultSet rs = ptmt.executeQuery();
            while(rs.next()) {
                Business item = new Business();
                item.setBusinessName(rs.getString("business_name"));
                item.setBusinessTel(rs.getString("business_tel"));
                item.setAddress(rs.getString("address"));
                result.add(item);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * insert business object with certain fields
     * @param newItem object with data inserted
     */
    public void quickInsert(Business newItem) {
        Connection conn = DBUtil.getConn();
        String sql = "INSERT INTO sample_business (" +
                "business_name, business_tel, website, create_user, create_time, update_user, update_time) " +
                "VALUES (" +
                "? , ? , ?, 'admin', current_time(), 'admin', current_time())";
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, newItem.getBusinessName());
            ptmt.setString(2, newItem.getBusinessTel());
            ptmt.setString(3, newItem.getWebsite());
            ptmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * insert new data
     * @param newItem
     */
    public void insertBusiness(Business newItem) {
        Connection conn = DBUtil.getConn();
        String[] fields = {"business_name", "business_tel", "business_fax", "website", "address", "postcode", "advantage_field",
                "bank_name", "bank_branch_name", "bank_number", "bank_represent", "stuff_num", "annual_sales", "transaction_deadline",
                "payment_sight", "account_kind", "comment", "principal_id", "status", "create_user", "update_user", "create_time", "update_time"};
        // generate sql sentence
        String[] values = new String[fields.length];

        for(int i = 0; i < fields.length; i ++) {
            values[i] = (!"create_time".equals(fields[i]) && !"update_time".equals(fields[i])) ? "?" : "current_time()";
        }
        String sql ="INSERT INTO sample_business (" +
                String.join(",", fields) + ")" +
                "VALUES " +
                "(" + String.join(",", values) + ")";
        try {
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

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void deleteBusinessById(Integer id) {
        Connection conn = DBUtil.getConn();
        String sql = "DELETE FROM sample_business WHERE id=?";
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setInt(1, id);
            ptmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Business getBusinessById(Integer id) {
        Connection conn = DBUtil.getConn();
        String sql = "SELECT * FROM sample_business WHERE id=?";
        Business business = new Business();
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setInt(1, id);
            // handle result
            ResultSet rs = ptmt.executeQuery();
            while(rs.next()) {
                business = (Business) DaoUtils.fillObject(rs, Business.class);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return business;
    }

    public void updateBusiness(Business business) {
        Connection conn = DBUtil.getConn();
        String[] updateFields = {"business_name=?", "business_tel=?", "business_fax=?", "website=?", "address=?", "postcode=?", "advantage_field=?",
                "bank_name=?", "bank_branch_name=?", "bank_number=?", "bank_represent=?", "stuff_num=?", "annual_sales=?", "transaction_deadline=?",
                "payment_sight=?", "account_kind=?", "comment=?", "principal_id=?", "status=?", "update_user=?", "update_time=current_time()"};
        String sql = "UPDATE sample_business SET " +
                String.join(",", updateFields) +
                "where id = ?";

        try {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
