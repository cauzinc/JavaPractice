package com.sample.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBUtil {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/Japan-project-sample?useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";

    private static Connection conn = null;
    private static Statement statement = null;

    static {
        System.out.println("loaded");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("connected");
        } catch (Exception e) {
            System.out.println("connect error");
            e.printStackTrace();
        }
    }

    public static Connection getConn() {
        return conn;
    }

    private static void testConn() {
        System.out.println("实例化Statement对象...");
        try {

            statement = conn.createStatement();
            String sql = "SELECT principle_name, department, position FROM sample_principle";
            ResultSet rs = statement.executeQuery(sql);

            while(rs.next()) {
                String principle_name = rs.getString("principle_name");
                String department = rs.getString("department");
                String position = rs.getString("position");
                System.out.printf("姓名: %s , 部门: %s, 职位: %s. \n", principle_name, department, position);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        testConn();
    }
}
