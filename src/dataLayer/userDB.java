package dataLayer;
import java.sql.*;

public class userDB {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/Auction";

    static final String USER = "#";
    static final String PASS = "#";

    public int retId(String username){
        Connection conn = null;
        Statement stmt = null;

        int flag=0;int id=-1;
        try {

            Class.forName("com.mysql.jdbc.Driver");

            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            System.out.println("username = "+username);
            String sql;
            sql = "SELECT * FROM users WHERE user_name = \"" +
                  username+"\"";

            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                 id = rs.getInt("id");
                System.out.println("after rs");
                flag=1;
            }

        }
            catch(SQLException se){
                se.printStackTrace();
            } catch(Exception e){
                e.printStackTrace();
            } finally{
                try{
                    if(stmt!=null)
                        stmt.close();
                } catch(SQLException se2){
                }
                try{
                    if(conn!=null)
                        conn.close();
                }catch(SQLException se){
                    se.printStackTrace();

                }

            System.out.println("Closing DB Connection - Goodbye!");
                if (flag==1) return id;
                else return 0;
            }

        }

    public boolean isValidUserLogin(String sUserName, String sUserPassword)
    {


        Connection conn = null;
        Statement stmt = null;
        boolean validLoginResult = false;

        try {
            Class.forName("com.mysql.jdbc.Driver");

            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
           sql = "SELECT * FROM users WHERE user_name = \"" +
                   sUserName + "\" AND user_password = \"" + sUserPassword + "\"";

            ResultSet rs = stmt.executeQuery(sql);

            if(rs.next()){
               validLoginResult = true;
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch(SQLException se){

            se.printStackTrace();
        } catch(Exception e){

            e.printStackTrace();
        } finally{

            try{
                if(stmt!=null)
                    stmt.close();
            } catch(SQLException se2){
            }
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        System.out.println("Closing DB Connection - Goodbye!");

        return validLoginResult;

    }

    public void signup(String username , String password,String email){

        Connection conn = null;
        Statement stmt = null;

        System.out.println(username +" "+password);

        try {

            Class.forName("com.mysql.jdbc.Driver");

            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            System.out.println("Creating statement...");
             stmt = conn.createStatement();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO users (user_name,user_password,email_id) VALUES (?,?,?)");
                    ps.setString(1,username);
                    ps.setString(2,password);
                    ps.setString(3,email);

                ps.executeUpdate();
            System.out.println("insert succ");

            stmt.close();
            conn.close();
        } catch(SQLException se){

            se.printStackTrace();
        } catch(Exception e){

            e.printStackTrace();
        } finally{

            try{
                if(stmt!=null)
                    stmt.close();
            } catch(SQLException se2){
            }
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        System.out.println("Closing DB Connection - Goodbye!");


    }

}