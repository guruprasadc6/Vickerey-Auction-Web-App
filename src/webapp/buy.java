package webapp;

import dataLayer.productDB;
import dataLayer.userDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "buy")
public class buy extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        userDB uo = new userDB();
        int uId = uo.retId(req.getParameter("username"));
        System.out.println("uId  ="+uId);
        if(uId!= 0){
            productDB po = new productDB();
            int pId = Integer.parseInt(req.getParameter("pId"));
            int baseprice = po.isProductIdValid(pId);
            System.out.println("baseprice  ="+baseprice);
            if(baseprice!= -1){
                int bidprice = Integer.parseInt(req.getParameter("bid"));
                if(bidprice>=baseprice){
                    po.addBid(pId,uId,bidprice);
                    req.getRequestDispatcher("/AuctionHomePage.jsp").forward(req,resp);

                }
                else
                {
                    req.setAttribute("errorMessage","Bid price is less than Base-price given by the seller.");
                  req.getRequestDispatcher("/buy.jsp").forward(req,resp);
                    return;
                }
            }
            else {
                req.setAttribute("errorMessage","Invalid product id. Check again.");
                req.getRequestDispatcher("/buy.jsp").forward(req,resp);
                return;
            }

        }
        else {
            req.setAttribute("errorMessage","Invalid username");
            req.getRequestDispatcher("/buy.jsp").forward(req,resp);

        }


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
