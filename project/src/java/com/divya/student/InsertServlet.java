/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.divya.student;

import com.divya.connect.Connect;
import java.sql.PreparedStatement;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Dell
 */
public class InsertServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String course = request.getParameter("course");
        String city = request.getParameter("city");

        try {
            
            Connection con = Connect.connect();

            java.sql.PreparedStatement ps = con.prepareStatement("insert into student values(?, ?, ?, ?)");

            java.sql.PreparedStatement check = con.prepareStatement("select id from student");
            ResultSet rs = check.executeQuery();

            boolean flag = true;
            while (rs.next()) {
                if (id == rs.getInt("id")) {
                    flag = false;
                    break;
                }
            }

            if (flag) {
                ps.setInt(1, id);
                ps.setString(2, name);
                ps.setString(3, course);
                ps.setString(4, city);
                ps.executeUpdate();
                out.print("<br>");
                out.print("<br>");
                out.print("<div align = 'center'>");
                out.print("Student's ID : " + id + "\n");
                out.print("<br>");
                out.print("Student's name : " + name + "\n");
                out.print("<br>");
                out.print("Student's course : " + course + "\n");
                out.print("<br>");
                out.print("Student's city :  " + city + "\n");
                out.print("<br>");
                out.print("<br>");
                out.print("<font color='blue'><h2>1 row inserted successfully</h2></font>");
                out.print("<br>");
            } else {
                out.print("<div align='center'>");
                out.println("<font color='red'><h2>0 row inserted</h2></font>");
            }
            
            out.print("<br>");
            out.print("<a href='student.html'><h3>HOME</h3></a>");
            out.print("</div>");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
