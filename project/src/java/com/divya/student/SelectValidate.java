package com.divya.student;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SelectValidate extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String pwd = request.getParameter("password");

        if (pwd.equals("welcome")) {

            RequestDispatcher rd = request.getRequestDispatcher("select.html");
            rd.forward(request, response);

        } else {

            out.println("<div align='center'");
            out.println("<br><br>");
            out.println("<font color='blue'><h1>WRONG PASSWORD</h1></font>");
            out.println("</div>");
            RequestDispatcher rd = request.getRequestDispatcher("validateSelect.html");
            rd.include(request, response);

        }

    }
    
}
