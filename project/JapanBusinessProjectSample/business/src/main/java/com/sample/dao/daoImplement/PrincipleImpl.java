package com.sample.dao.daoImplement;

import com.sample.dao.IPrincipleDao;
import com.sample.pojo.Business;
import com.sample.pojo.Principle;
import com.sample.utils.DBUtil;
import com.sample.utils.DaoUtils;
import com.sample.vo.PrincipleListItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PrincipleImple implements IPrincipleDao {
    public List<PrincipleListItem> getPrincipleRelatedList() {
        Connection conn = DBUtil.getConn();
        String sql = "SELECT principle_name, business_name, tel " +
                "FROM sample_business, sample_principle " +
                "WHERE sample_business.principal_id = sample_principle.id";
        List<PrincipleListItem> result = new ArrayList<>();
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ResultSet rs = ptmt.executeQuery();
            while(rs.next()) {
                PrincipleListItem item = new PrincipleListItem();
                item.setBusinessName(rs.getString("business_name"));
                item.setPrincipleName(rs.getString("principle_name"));
                item.setTel(rs.getString("tel"));
                result.add(item);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public void insertPrinciple(Principle principle) {
        Connection conn = DBUtil.getConn();

        String[] fields = {"principle_name", "department", "position", "mail", "tel", "phone", "fax",
                "address", "postcode", "favorite", "comment", "create_user", "update_user", "create_time", "update_time"};
        // generate sql sentence
        String[] values = new String[fields.length];

        for(int i = 0; i < fields.length; i ++) {
            values[i] = (!"create_time".equals(fields[i]) && !"update_time".equals(fields[i])) ? "?" : "current_time()";
        }

        String sql ="INSERT INTO sample_principle (" +
                String.join(",", fields) + ")" +
                "VALUES " +
                "(" + String.join(",", values) + ")";
        try {
            // execute
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, principle.getPrincipleName());
            ptmt.setString(2, principle.getDepartment());
            ptmt.setString(3, principle.getPosition());
            ptmt.setString(4, principle.getMail());
            ptmt.setString(5, principle.getTel());
            ptmt.setString(6, principle.getPhone());
            ptmt.setString(7, principle.getFax());
            ptmt.setString(8, principle.getAddress());
            ptmt.setString(9, principle.getPostcode());
            ptmt.setString(10, principle.getFavorite());
            ptmt.setString(11, principle.getComment());
            ptmt.setString(12, "admin");
            ptmt.setString(13, "admin");
            ptmt.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updatePrinciple(Principle principle) {
        Connection conn = DBUtil.getConn();

        String[] fields = {"principle_name=?", "department=?", "position=?", "mail=?", "tel=?", "phone=?", "fax=?",
                "address=?", "postcode=?", "favorite=?", "comment=?", "update_user=?",  "update_time=current_time()"};


        String sql ="UPDATE sample_principle SET " +
                String.join(",", fields) +
                "WHERE id = ? ";
        try {
            // execute
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, principle.getPrincipleName());
            ptmt.setString(2, principle.getDepartment());
            ptmt.setString(3, principle.getPosition());
            ptmt.setString(4, principle.getMail());
            ptmt.setString(5, principle.getTel());
            ptmt.setString(6, principle.getPhone());
            ptmt.setString(7, principle.getFax());
            ptmt.setString(8, principle.getAddress());
            ptmt.setString(9, principle.getPostcode());
            ptmt.setString(10, principle.getFavorite());
            ptmt.setString(11, principle.getComment());
            ptmt.setString(12, "admin");
            ptmt.setInt(13, principle.getId());
            ptmt.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deletePrinciple(Integer id) {
        Connection conn = DBUtil.getConn();
        String sql = "DELETE FROM sample_principle WHERE id=?";
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setInt(1, id);
            ptmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Principle getPrincipleById(Integer id) {
        Connection conn = DBUtil.getConn();
        String sql = "SELECT * FROM sample_principle WHERE id=?";
        Principle principle = new Principle();
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setInt(1, id);
            // handle result
            ResultSet rs = ptmt.executeQuery();
            while(rs.next()) {
                principle = (Principle) DaoUtils.fillObject(rs, Principle.class);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return principle;
    }



}
