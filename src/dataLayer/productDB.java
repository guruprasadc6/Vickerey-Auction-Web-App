package dataLayer;

import java.sql.*;

public class productDB {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/Auction";

    static final String USER = "#";
    static final String PASS = "#";


    public int addProduct(String product_name, String product_description, int  product_baseprice, int  product_duration, String product_category, String username,long time) {

        userDB u = new userDB();
        int id = u.retId(username);
        if (id == 0) return 0;
        System.out.println("inside addProduct");
        Connection conn = null;
        Statement stmt = null;
        int pId=-1;
        try {

            Class.forName("com.mysql.jdbc.Driver");

            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            System.out.println(product_name+product_baseprice+product_category+product_description+product_duration+id);

            PreparedStatement ps =conn.prepareStatement("insert into auction_items (product_name,product_description,product_baseprice,product_category,product_duration,seller_id,product_time) VALUES (?,?,?,?,?,?,?)");
            ps.setString(1,product_name);
            ps.setString(2,product_description);


            ps.setString(4,product_category);
            ps.setInt(3,product_baseprice);
            ps.setInt(5,product_duration);
            ps.setInt(6,id);
            ps.setLong(7,time);
            ps.executeUpdate();
            String sql  ="select * from auction_items";
             ResultSet rs = stmt.executeQuery(sql);

             while (rs.next()){
                 pId = rs.getInt("product_id");
             }
            System.out.println("insert into product db success");


        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {

            e.printStackTrace();
        } finally {

            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
            return pId;
        }

    }

    public int isProductIdValid(int pId)
    {
        Connection conn = null;
        Statement stmt = null;
        int id = -1;
        try {

            Class.forName("com.mysql.jdbc.Driver");

            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "select * from auction_items where product_id = \"" + pId+"\"";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next())
            {
                System.out.println("id found " );
                id = rs.getInt(5);
            }
        }
        catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {

            e.printStackTrace();
        } finally {

            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();

            }
            return id;
        }

    }

    public  void  addBid(int pId,int bId,int bid_value){
        Connection conn = null;


        try {

            Class.forName("com.mysql.jdbc.Driver");

            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            System.out.println("Creating statement...");
            PreparedStatement ps = conn.prepareStatement("INSERT into bids (product_id,bidders_id,bid_value) VALUES (?,?,?)");
            ps.setInt(1,pId);
            ps.setInt(2,bId);
            ps.setInt(3,bid_value);
            ps.executeUpdate();
            System.out.println("insert into bids succs");

        }
        catch (SQLException se) {

            se.printStackTrace();
        } catch (Exception e) {

            e.printStackTrace();
        } finally {

            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    public int bidRes(int pId){

        Connection conn = null;
        Statement stmt = null;
        int firstHigh=0,secondHigh=0,bId1=-1,bId2=-1,finalBid=-1;
        try {

            Class.forName("com.mysql.jdbc.Driver");

            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;

            sql= "select * from bids where product_id = \"" + pId+"\"";
            ResultSet rs =stmt.executeQuery(sql);
            while (rs.next()){
                if(rs.getInt("bid_value") > firstHigh){
                    secondHigh =firstHigh;
                    bId2 = bId1;
                    firstHigh= rs.getInt("bid_value");
                    bId1 = rs.getInt("bidders_id");

                }
            }
            System.out.println("secondHigh = "+secondHigh);

            if(bId2>0){
                finalBid=bId1;

            }
            else{ finalBid=bId1 ; secondHigh = firstHigh;}
            if(finalBid>0){
                productDB po = new productDB();
                po.addSellerId(pId,finalBid,secondHigh);
            }
        }
        catch (SQLException se) {

            se.printStackTrace();
        } catch (Exception e) {

            e.printStackTrace();
        } finally {

            try {
                if (conn != null)
                    conn.close();
                stmt.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
            if(bId2>0) return bId1;
            else if(firstHigh==0) return -1;
            else return bId1;

        }
    }

    public void addSellerId(int pId,int bId,int soldPrice){

        Connection conn = null;
        Statement stmt = null;

        try {

            Class.forName("com.mysql.jdbc.Driver");

            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            System.out.println("Creating statement...");
            stmt = conn.createStatement();

            PreparedStatement ps =conn.prepareStatement("UPDATE auction_items SET buyer_id = ? , isSold = ?,sold_price = ? WHERE product_id = ?");
            ps.setInt(1,bId);
            ps.setInt(2,1);
            ps.setInt(3,soldPrice);
            ps.setInt(4,pId);
            ps.executeUpdate();
        }

        catch (SQLException se) {

            se.printStackTrace();
        } catch (Exception e) {

            e.printStackTrace();
        } finally{

            try {
                if (conn != null)
                    conn.close();
                stmt.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
}