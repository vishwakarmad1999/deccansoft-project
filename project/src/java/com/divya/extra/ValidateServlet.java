/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.divya.extra;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Dell
 */
public class ValidateServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
            response.setContentType("text/html;charset=UTF-8");
            
            PrintWriter out = response.getWriter();
            
            String uname = request.getParameter("uname");
            String pwd = request.getParameter("password");
            
            if (pwd.equals("123456") && uname.equals("localhost")) {
                RequestDispatcher rd = request.getRequestDispatcher("home.html");
                rd.forward(request, response);
            }
            else {
                out.print("<div align='center'");
                out.print("<br><br>");
                out.print("<font color='red'><h1>Incorrect username or password</h1></font>");
                out.print("</div>");
                RequestDispatcher rd = request.getRequestDispatcher("login.html");
                rd.include(request, response);
                
            }
        
    }

}
