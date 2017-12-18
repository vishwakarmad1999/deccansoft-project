package com.divya.faculty;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InsertServletF extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
  
         response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String subject = request.getParameter("subject");

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/institute", "root", "welcome");

            java.sql.PreparedStatement ps = con.prepareStatement("insert into faculty values(?, ?, ?)");

            java.sql.PreparedStatement check = con.prepareStatement("select id from faculty");
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
                ps.setString(3, subject);
                ps.executeUpdate();
                out.print("<br>");
                out.print("<br>");
                out.print("<div align = 'center'>");
                out.print("Faculty's ID : " + id + "\n");
                out.print("<br>");
                out.print("Faculty's name : " + name + "\n");
                out.print("<br>");
                out.print("Faculty's course : " + subject + "\n");
                out.print("<br>");
                out.print("<br>");
                out.print("<font color='blue'><h2>1 row inserted successfully</h2></font>");
                out.print("<br>");
            } else {
                out.print("<div align='center'>");
                out.println("<font color='red'><h2>0 row inserted</h2></font>");
            }
            
            out.print("<br>");
            out.print("<a href='faculty.html'><h3>HOME</h3></a>");
            out.print("</div>");

        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

}
