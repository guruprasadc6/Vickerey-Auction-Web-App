package webapp;

import applayer.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "login")
public class login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute("username",request.getParameter("loginname"));
        request.setAttribute("password",request.getParameter("password"));

        User userOb = new User();

        if(userOb.isValidUserCredentials(request.getParameter("loginname"),request.getParameter("password"))) {
            request.getRequestDispatcher("/AuctionHomePage.jsp").forward(request,response);
        }
        else
        {
            request.setAttribute("errorMessage","Invalid login or password");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        out.print("login-name"+request.getParameter("loginname")+"Password"+request.getParameter("password"));
    }
}
