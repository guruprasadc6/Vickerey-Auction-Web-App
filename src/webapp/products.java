package webapp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.sql.*;

@WebServlet(name = "products")
public class products extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        String connectionURL = "jdbc:mysql://localhost:3306/Auction";

        Connection connection = null;

        ResultSet rs;

        List ProductList1 = new ArrayList();
        List<Integer> ProductList2 = new ArrayList<>();

        try {

            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection(connectionURL, "#", "#");

            String sql = "select * from auction_items where isSold = '0'";

            Statement s = connection.createStatement();

            s.executeQuery(sql);

            rs = s.getResultSet();

            while (rs.next ()){

                ProductList1.add(rs.getInt("product_id"));
                ProductList1.add(rs.getString("product_name"));
                ProductList1.add(rs.getString("product_description"));
                ProductList1.add(rs.getString("product_category"));
                ProductList2.add(rs.getInt("product_baseprice"));
                ProductList2.add(rs.getInt("product_duration"));
            }

            rs.close ();

            s.close ();

        }catch(Exception e){

        }

        request.setAttribute("data",ProductList1);
        request.setAttribute("data2",ProductList2);

        RequestDispatcher dispatcher = request.getRequestDispatcher("products.jsp");

        if (dispatcher != null){
            dispatcher.forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();

        String connectionURL = "jdbc:mysql://localhost:3306/Auction";

        Connection connection = null;

        ResultSet rs;

        List ProductList1 = new ArrayList();
        List<Integer> ProductList2 = new ArrayList<>();

        try {

            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection(connectionURL, "#", "#");

            String sql = "select * from auction_items where isSold = '0'";

            Statement s = connection.createStatement();

            s.executeQuery(sql);

            rs = s.getResultSet();

            while (rs.next ()){
                ProductList1.add(rs.getInt("product_id"));
                ProductList1.add(rs.getString("product_name"));
                ProductList1.add(rs.getString("product_description"));
                ProductList1.add(rs.getString("product_category"));
                ProductList2.add(rs.getInt("product_baseprice"));
                ProductList2.add(rs.getInt("product_duration"));
            }

            rs.close ();

            s.close ();

        }catch(Exception e){


        }

        request.setAttribute("data",ProductList1);
        request.setAttribute("data2",ProductList2);

        RequestDispatcher dispatcher = request.getRequestDispatcher("products.jsp");

        if (dispatcher != null){
            dispatcher.forward(request,response);
        }
    }
}
