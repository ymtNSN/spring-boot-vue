package com.ymt.manage;

import com.sun.org.apache.regexp.internal.RE;

import java.sql.*;

/**
 * @Description TODO
 * @Author yangmingtian
 * @Date 2019/5/7
 */
public class Main {
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://rm-uf6q7l04721ze2mbfro.mysql.rds.aliyuncs.com:3306/change_test?useSSL=false&serverTimezone=UTC", "change_dev", "xP6FQ4ajw79W");
            stmt = conn.createStatement();
            String sql = "select * from user_task_enum";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                System.out.println(rs);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
