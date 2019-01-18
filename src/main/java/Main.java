import context.DBConnect;
import dao.ReportFormDAO;
import dao.UltraSoundResultDAO;
import entity.ConnInfo;
import entity.UltraSoundResult;

import java.sql.Connection;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args)  {
        DBConnect dbConnect = new DBConnect();

        ConnInfo connInfoMysql = new ConnInfo("localhost", "3306", "clinic", "duc010298", "123456");
        Connection connMysql = dbConnect.getConnectionMySql(connInfoMysql);
        UltraSoundResultDAO resultDAO = new UltraSoundResultDAO(connMysql);
        ArrayList<UltraSoundResult> soundResults = resultDAO.getAllResult();
        System.out.println(soundResults.size());

        ConnInfo connInfoPostgres = new ConnInfo("localhost", "5432", "clinic", "postgres", "123456");
        Connection connPostgres = dbConnect.getConnectionPostgres(connInfoPostgres);
        ReportFormDAO reportFormDAO = new ReportFormDAO(connPostgres);
        int n = reportFormDAO.deleteAllReport();
        System.out.println(n);
        n = reportFormDAO.insertReport(soundResults);
        System.out.println(n);
    }
}
