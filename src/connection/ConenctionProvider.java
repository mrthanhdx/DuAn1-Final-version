package connection;

import java.sql.*;

public class ConenctionProvider {
    // Connection
    private static Connection conn;
    private static String DB_SERVERNAME = "localhost";
    private static String DB_DATABASENAME = "DuAn1";
    private static String DB_USERNAME = "sa";
    private static String DB_PASSWORD = "12345678";
   
    public static Connection getConnection () {
        if (conn!=null) {
            return conn;
        } else {
             String URL = String.format("jdbc:sqlserver://%s;databaseName=%s;trustServerCertificate=true;", DB_SERVERNAME,DB_DATABASENAME);
        try {
            conn = DriverManager.getConnection(URL,DB_USERNAME,DB_PASSWORD);
            System.out.println(conn);
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        }
    }
    public static void main(String[] args) {
        getConnection();
    }
}
