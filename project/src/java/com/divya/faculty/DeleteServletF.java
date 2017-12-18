package com.divya.faculty;

import com.divya.connect.Connect;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class DeleteServletF extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();

        int id = Integer.parseInt(request.getParameter("id"));

        try {

            Connection con = Connect.connect();
            
            PreparedStatement ps = con.prepareStatement("delete from faculty where id = ?");

            PreparedStatement check = con.prepareStatement("select * from faculty");

            ResultSet rs = check.executeQuery();

            boolean flag = false;

            while (rs.next()) {
                if (id == rs.getInt(1)) {
                    flag = true;
                    break;
                }
            }

            out.print("<div align = 'center'>");
            out.print("<br>");
            out.print("<br>");
            out.print("<br>");

            if (flag) {

                ps.setInt(1, id);
                ps.executeUpdate();
                out.print("<font color = 'blue'><h1>1 row deleted</h1></font>");
                
            } else {

                out.print("<font color = 'red'><h1>0 row deleted</h1></font>");
                
            }
            
            out.print("<br>");
            out.print("<a href='student.html'><h3>HOME</h3></a>");
            out.print("</div>");
            
        } 
        catch (Exception e) {
        
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
