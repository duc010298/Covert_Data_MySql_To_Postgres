import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import context.DBConnect;
import dao.CustomerMySqlDAO;
import dao.UltraSoundResultDAO;
import entity.ConnInfo;
import entity.CustomerMySql;
import entity.CustomerPostgres;
import entity.UltraSoundResult;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class MainServer {

    private static Gson gson;
    private static final int rowOnPage = 100;
    private static String serverUrl;

    public static void main(String[] args) {
        MainServer mainServer = new MainServer();
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("yyyy-MM-dd");
        gson = gsonBuilder.create();

        System.out.println("========== Convert Data MySQL to Postgres ==========");
        System.out.println("--- Enter MySQL connection information");
        Scanner sc = new Scanner(System.in);
        System.out.print("Host:");
        String host = sc.nextLine();
        System.out.print("Port:");
        String port = sc.nextLine();
        System.out.print("Database:");
        String database = sc.nextLine();
        System.out.print("User:");
        String user = sc.nextLine();
        System.out.print("Password:");
        String pass = sc.nextLine();

        serverUrl = "https://phongkham158.herokuapp.com/";
//        System.out.println("--- Enter server URL");
//        System.out.print("URL:");
//        serverUrl = sc.nextLine();


        DBConnect dbConnect = new DBConnect();
        ConnInfo connInfoMysql = new ConnInfo(host, port, database, user, pass);
        Connection connMysql = dbConnect.getConnectionMySql(connInfoMysql);

        System.out.println("-- Get report info from MySQL");
        UltraSoundResultDAO resultDAO = new UltraSoundResultDAO(connMysql);
        ArrayList<UltraSoundResult> soundResults = resultDAO.getAllResult();
        System.out.println(soundResults.size() + " records");
        System.out.println("-- Send report info to server");
        try {
            for (UltraSoundResult u : soundResults) {
                mainServer.sendPostReport(u);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("--- Get total customer on MySQL");
        CustomerMySqlDAO customerMySqlDAO = new CustomerMySqlDAO(connMysql);
        int totalCustomer = customerMySqlDAO.getTotalCustomer();
        System.out.println(totalCustomer + " customer");
        System.out.println("-- Send customer from MySQL to Server");
        int totalPage = (int) Math.ceil(Double.valueOf(totalCustomer) / Double.valueOf(rowOnPage));
        ArrayList<CustomerMySql> customers;
        try {
            for (int i = 1; i <= totalPage; i++) {
                customers = customerMySqlDAO.getCustomerByPage(i, rowOnPage);
                for (CustomerMySql cMySql : customers) {
                    CustomerPostgres customerPostgres = mainServer.convertCustomer(cMySql);
                    mainServer.sendPostCustomer(customerPostgres);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private CustomerPostgres convertCustomer(CustomerMySql customerMySql) {
        CustomerPostgres customerPostgres = new CustomerPostgres();
        customerPostgres.setCustomerName(customerMySql.getName());
        customerPostgres.setNameSearch(customerMySql.getNameS());
        customerPostgres.setYob(customerMySql.getyOB());
        customerPostgres.setAddress(customerMySql.getAddressCus());
        customerPostgres.setAddressSearch(customerMySql.getAddressCusS());
        customerPostgres.setDayVisit(convertUtilToSql(customerMySql.getDayVisit()));
        customerPostgres.setExpectedDob(convertUtilToSql(customerMySql.getExpectedDOB()));
        customerPostgres.setResult(customerMySql.getResult());
        customerPostgres.setNote(customerMySql.getNote());
        customerPostgres.setReport(customerMySql.getReport());
        return customerPostgres;
    }

    private java.sql.Date convertUtilToSql(Date uDate) {
        if(uDate == null) return null;
        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        return sDate;
    }

    private void sendPostCustomer(CustomerPostgres customerPostgres) throws IOException {
        String json = gson.toJson(customerPostgres);

        CloseableHttpClient client = HttpClients.createDefault();
        StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
        HttpPost request = new HttpPost(serverUrl + "/backdoor/customer/add");
        request.setEntity(entity);
        client.execute(request);
        client.close();
    }

    private void sendPostReport(UltraSoundResult soundResult) throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(serverUrl + "/backdoor/form/add");
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("name", soundResult.getName()));
        params.add(new BasicNameValuePair("content", soundResult.getContent()));
        httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
        client.execute(httpPost);
        client.close();
    }
}
