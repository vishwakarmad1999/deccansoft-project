package com.divya.faculty;

import com.divya.connect.Connect;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SelectServletF extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();

        response.setContentType("text/html; charset=UTF-8");

        try {

            Connection con = Connect.connect();

            Statement s = con.createStatement();

            ResultSet rs = s.executeQuery("select count(*) from faculty");
            
            rs.next();
            int rows = rs.getInt(1);

            rs = s.executeQuery("select id, name, subject from faculty");
            ResultSetMetaData rm = rs.getMetaData();

            out.print("<body bgcolor='indigo'>");
            out.print("<div align='center' >");
            out.print("<br>");
            out.print("<br>");
            out.print("<br>");
            out.print("<h2>DATA IN THE FACULTY TABLE</h2>");
            out.print("<br>");
            out.print("<br>");
            out.print("<table border='1' bgcolor='white'>");
            out.print("<tr>");

            for (int i = 1; i <= rm.getColumnCount(); i++) {

                out.print("<th>" + rm.getColumnName(i).toUpperCase()+ "</th>");

            }
            out.print("</tr>");

            while (rs.next()) {

                out.print("<tr>");
                out.print("<td>");
                out.print(rs.getInt(1));
                out.print("</td>");
                out.print("<td>");
                out.print(rs.getString(2));
                out.print("</td>");
                out.print("<td>");
                out.print(rs.getString(3));
                out.print("</td>");
                out.print("</tr>");

            }
            out.print("</table>");
            out.print("<br>");
            out.print("<br>");
            out.print("<h2>Row(s) : " + rows + "</h2>");
            out.print("<br>");
            out.print("<a href = 'faculty.html'><h2 style='color:red;'>HOME</h2></a>");
            out.print("</div>");
            out.print("</body>");

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

}
