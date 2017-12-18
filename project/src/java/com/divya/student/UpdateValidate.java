/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.divya.student;

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
public class UpdateValidate extends HttpServlet {

    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        
        PrintWriter out = response.getWriter();
        
        String pwd = request.getParameter("password");
        
        if (pwd.equals("welcome")) {
            
            RequestDispatcher rd = request.getRequestDispatcher("update.html");
            rd.forward(request, response);
            
        }
        else {
            
            out.println("<div align='center'");
            out.println("<br><br>");
            out.println("<font color='red'><h1>WRONG PASSWORD</h1></font>");
            out.println("</div>");
            RequestDispatcher rd = request.getRequestDispatcher("validateUpdate.html");
            rd.include(request, response);
            
        }
        
    }

}
