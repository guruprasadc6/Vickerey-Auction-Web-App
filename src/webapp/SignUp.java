package webapp;

import applayer.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "signup")
public class SignUp extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("inside doPost");
        PrintWriter out = response.getWriter();
        out.print("from doGet");
        out.println("user-name"+request.getParameter("username")+"  Password"+request.getParameter("password"));

        request.setAttribute("username",request.getParameter("username"));
        request.setAttribute("password",request.getParameter("password"));

        User userOb = new User();

        userOb.signupSuccess(request.getParameter("username"),request.getParameter("password"),request.getParameter("email"));
        request.getRequestDispatcher("/AuctionHomePage.jsp").forward(request, response);
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        out.print("user-name"+request.getParameter("username")+"Password"+request.getParameter("password"));
    }
}
