package dao;

import entity.CustomerMySql;

import java.sql.*;
import java.util.ArrayList;

public class CustomerPostgresDAO {
    private Connection conn;

    public CustomerPostgresDAO(Connection conn) {
        this.conn = conn;
    }

    public int deleteCustomer() {
        String sql = "delete from customer";

        try (Statement sta = conn.createStatement()) {
            int n = sta.executeUpdate(sql);
            return n;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int insertCustomer(ArrayList<CustomerMySql> customers) {
        String sql = "insert into customer (customer_name, name_search, YOB, address, address_search, day_visit, expectedDOB, result, note, report) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pre = conn.prepareStatement(sql)) {
            for (CustomerMySql c : customers) {
                pre.setString(1, c.getName());
                pre.setString(2, c.getNameS());
                pre.setInt(3, c.getyOB());
                pre.setString(4, c.getAddressCus());
                pre.setString(5, c.getAddressCusS());
                pre.setDate(6, new java.sql.Date(c.getDayVisit().getTime()));
                if (c.getExpectedDOB() == null) {
                    pre.setNull(7, Types.DATE);
                } else {
                    pre.setDate(7, new java.sql.Date(c.getExpectedDOB().getTime()));
                }
                pre.setString(8, c.getResult());
                pre.setString(9, c.getNote());
                pre.setString(10, c.getReport());
                pre.addBatch();
            }
            int[] n = pre.executeBatch();
            int count = 0;
            for (int i : n) {
                count += i;
            }
            return count;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
