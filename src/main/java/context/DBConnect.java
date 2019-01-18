package context;

import entity.ConnInfo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {

    public Connection getConnectionMySql(ConnInfo connInfo) {
        Connection conn;
        String url = "jdbc:mysql://" + connInfo.getHost() + ":" + connInfo.getPort() + "/" + connInfo.getDatabaseName() + "?autoReconnect=true&useUnicode=true&characterEncoding=UTF-8";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, connInfo.getUser(), connInfo.getPassword());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return conn;
    }

    public Connection getConnectionPostgres(ConnInfo connInfo) {
        Connection conn;
        String url = "jdbc:postgresql://" + connInfo.getHost() + ":" + connInfo.getPort() + "/" + connInfo.getDatabaseName();
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(url, connInfo.getUser(), connInfo.getPassword());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return conn;
    }


}