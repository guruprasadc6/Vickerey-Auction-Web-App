package webapp;
import dataLayer.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Timer;

@WebServlet(name = "sell")
public class sell extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("inside sell.java");
        productDB po = new productDB();
        int bp = Integer.parseInt(req.getParameter("baseprice"));
        int dur = Integer.parseInt(req.getParameter("duration"));
        long time = (new Date().getTime())/1000;
        int pId = po.addProduct(req.getParameter("product_name"),req.getParameter("product_description"),bp,dur,req.getParameter("category"),req.getParameter("username"),time);
        req.getRequestDispatcher("/AuctionHomePage.jsp").forward(req,resp);
        if(pId>0){
            dur = dur*60*1000;
            Timer t = new Timer();
            t.schedule(new java.util.TimerTask(){

                public  void  run(){
                   productDB po1 = new productDB();
                  System.out.println("bId = "+po1.bidRes(pId));
                }
            },dur);

        }
        else
        {
            req.setAttribute("errorMessage","Invalid username");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
