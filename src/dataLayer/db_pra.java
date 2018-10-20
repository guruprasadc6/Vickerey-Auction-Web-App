package dataLayer;

import java.sql.*;

public class db_pra {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/Auction";

    static final String USER = "#";
    static final String PASS = "#";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        boolean validLoginResult = false;

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            System.out.println("Creating statement...");
            stmt = conn.createStatement();
           String sql;

           System.out.println("outside run");
           new java.util.Timer().schedule( new java.util.TimerTask() {
               public  void run(){
                   System.out.println("inside run 1");
                   cancel();
               }
           } ,10000);

            new java.util.Timer().schedule( new java.util.TimerTask() {
                public  void run(){
                    System.out.println("inside run 2");
                    cancel();
                }
            } ,5000);
        }
        catch (Exception e) {System.out.println(e);
        }
    }
}