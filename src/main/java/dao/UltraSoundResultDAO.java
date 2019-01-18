package dao;

import entity.UltraSoundResult;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UltraSoundResultDAO {
    private Connection conn;

    public UltraSoundResultDAO(Connection connection) {
        this.conn = connection;
    }

    public ArrayList<UltraSoundResult> getAllResult() {
        ArrayList<UltraSoundResult> ultraSoundResults = new ArrayList<>();

        String sql = "select * from UltraSoundResult";
        try (Statement sta = conn.createStatement()){
            try (ResultSet rs = sta.executeQuery(sql)) {
                while (rs.next()) {
                    int id = rs.getInt(1);
                    String name = rs.getString(2);
                    int orderNumber = rs.getInt(3);
                    String content = rs.getString(4);
                    UltraSoundResult ultraSoundResult = new UltraSoundResult(id, name, orderNumber, content);
                    ultraSoundResults.add(ultraSoundResult);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return ultraSoundResults;
    }
}
