import context.DBConnect;
import dao.CustomerMySqlDAO;
import dao.CustomerPostgresDAO;
import dao.ReportFormDAO;
import dao.UltraSoundResultDAO;
import entity.ConnInfo;
import entity.CustomerMySql;
import entity.UltraSoundResult;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static final int rowOnPage = 100;

    public static void main(String[] args) {


        System.out.println("========== Convert Data MySQL to Postgres ==========");
        System.out.println("--- Step 1: Enter MySQL connection information");
        ConnInfo connInfoMysql = inputInfo();
        System.out.println("--- Step 2: Enter Postgres connection information");
        ConnInfo connInfoPostgres = inputInfo();
        System.out.println("=====================================================");
        DBConnect dbConnect = new DBConnect();

        System.out.println("-- Step 3: Initialize connection to MySQL");
        Connection connMysql = dbConnect.getConnectionMySql(connInfoMysql);
        System.out.println("=====================================================");
        System.out.println("-- Step 4: Initialize connection to Postgres");
        Connection connPostgres = dbConnect.getConnectionPostgres(connInfoPostgres);
        System.out.println("=====================================================");

        System.out.println("-- Step 5: Get report info from MySQL");
        UltraSoundResultDAO resultDAO = new UltraSoundResultDAO(connMysql);
        ArrayList<UltraSoundResult> soundResults = resultDAO.getAllResult();
        System.out.println(soundResults.size() + " records");
        System.out.println("=====================================================");
        System.out.println("-- Step 6: Delete all report info on Postgres");
        ReportFormDAO reportFormDAO = new ReportFormDAO(connPostgres);
        System.out.println(reportFormDAO.deleteAllReport() + " records have been deleted");
        System.out.println("=====================================================");
        System.out.println("-- Step 7: Insert report info to Postgres");
        System.out.println(reportFormDAO.insertReport(soundResults) + " records have been inserted");
        System.out.println("=====================================================");
        System.out.println("--- Step 8: Get total customer on MySQL");
        CustomerMySqlDAO customerMySqlDAO = new CustomerMySqlDAO(connMysql);
        int totalCustomer = customerMySqlDAO.getTotalCustomer();
        System.out.println(totalCustomer + " customer");
        System.out.println("=====================================================");
        System.out.println("--- Step 9: Delete all customer on Postgres");
        CustomerPostgresDAO customerPostgresDAO = new CustomerPostgresDAO(connPostgres);
        System.out.println(customerPostgresDAO.deleteCustomer() + " records have been deleted");
        System.out.println("=====================================================");
        System.out.println("-- Step 10: Transfer customer from MySQL to Postgres");
        int totalPage = (int) Math.ceil(Double.valueOf(totalCustomer) / Double.valueOf(rowOnPage));
        ArrayList<CustomerMySql> customers;
        int count = 0;
        for (int i = 1; i <= totalPage; i++) {
            customers = customerMySqlDAO.getCustomerByPage(i, rowOnPage);
            count += customerPostgresDAO.insertCustomer(customers);
        }
        System.out.println(count + " records have been inserted");
    }

    private static ConnInfo inputInfo() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Host: ");
        String host = sc.nextLine();
        System.out.print("Port: ");
        String port = sc.nextLine();
        System.out.print("Database: ");
        String database = sc.nextLine();
        System.out.print("User: ");
        String user = sc.nextLine();
        System.out.print("Password: ");
        String pass = sc.nextLine();
        ConnInfo connInfo = new ConnInfo(host, port, database, user, pass);
        return connInfo;
    }
}
