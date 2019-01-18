package dao;

import entity.CustomerMySql;

import java.sql.*;
import java.util.ArrayList;

public class CustomerMySqlDAO {
    private Connection conn;

    public CustomerMySqlDAO(Connection conn) {
        this.conn = conn;
    }

    public int getTotalCustomer() {
        String sql = "select COUNT(id) from Customer";

        try (Statement sta = conn.createStatement()) {
            try (ResultSet rs = sta.executeQuery(sql)) {
                rs.next();
                return rs.getInt(1);
            } catch (SQLException e) {
                e.printStackTrace();
                return 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public ArrayList<CustomerMySql> getCustomerByPage(int page, int rowOnPage) {
        ArrayList<CustomerMySql> customerMySqls = new ArrayList<>();
        int index = (page - 1) * rowOnPage;

        String sql = "select * from Customer order by id limit ?, ?";

        try (PreparedStatement pre = conn.prepareStatement(sql)) {
            pre.setInt(1, index);
            pre.setInt(2, rowOnPage);
            try (ResultSet rs = pre.executeQuery()) {
                while(rs.next()) {
                    int id = rs.getInt(1);
                    String name = rs.getString(2);
                    String nameS = rs.getString(3);
                    int yOB = rs.getInt(4);
                    String addressCus = rs.getString(5);
                    String addressCusS = rs.getString(6);
                    java.util.Date dateVisit = rs.getDate(7);
                    java.util.Date expectedDOB = rs.getDate(8);
                    String result = rs.getString(9);
                    String note = rs.getString(10);
                    String report = rs.getString(11);
                    CustomerMySql customerMySql = new CustomerMySql(id, name, nameS, yOB, addressCus, addressCusS, dateVisit, expectedDOB, result, note, report);
                    customerMySqls.add(customerMySql);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return customerMySqls;
    }
}
