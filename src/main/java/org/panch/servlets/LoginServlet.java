package org.panch.servlets;

import org.panch.util.UserType;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        if(!username.equals("panch") && !username.equals("user")){
            RequestDispatcher view = request.getRequestDispatcher("main.jsp");
            view.forward(request, response);
        } else {
            HttpSession session = request.getSession();
            if(username.equals("panch")){
                session.setAttribute("userType", UserType.Admin);
            }
            if(username.equals("user")){
                session.setAttribute("userType", UserType.User);
            }
            response.sendRedirect("/employee.do");
        }
    }
}


