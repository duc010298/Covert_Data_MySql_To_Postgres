package dao;

import entity.UltraSoundResult;

import java.sql.*;
import java.util.ArrayList;

public class ReportFormDAO {
    private Connection conn;

    public ReportFormDAO(Connection conn) {
        this.conn = conn;
    }

    public int deleteAllReport() {

        String sql = "delete from report_form";
        try (Statement sta = conn.createStatement()) {
            int n = sta.executeUpdate(sql);
            return n;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int insertReport(ArrayList<UltraSoundResult> ultraSoundResults) {
        String sql = "insert into report_form (report_name, order_number, content) values (?, ?, ?)";

        try (PreparedStatement pre = conn.prepareStatement(sql)) {
            for (UltraSoundResult u : ultraSoundResults) {
                pre.setString(1, u.getName());
                pre.setInt(2, u.getOrderNumber());
                pre.setString(3, u.getContent());
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
