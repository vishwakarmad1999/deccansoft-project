package com.divya.student;

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

public class SelectServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();

        try {

            Connection con = Connect.connect();

            Statement s = con.createStatement();

            ResultSet rs = s.executeQuery("select count(*) from student");

            rs.next();

            int rows = rs.getInt(1);

            out.print("<body bgcolor = 'brown'>");
            out.print("<div align = 'center'>");
            out.print("<br>");
            out.print("<br>");
            out.print("<br>");
            out.print("<h1>DATA IN THE STUDENT</h1>");

            if (rows != 0) {

                rs = s.executeQuery("select * from student");

                ResultSetMetaData rm = rs.getMetaData();

                out.print("<table border = '1' bgcolor = 'white'>");

                out.print("<tr>");
                for (int i = 1; i <= rm.getColumnCount(); i++) {

                    out.print("<th>" + rm.getColumnName(i).toUpperCase() + "</th>");

                }
                out.print("</tr>");

                while (rs.next()) {

                    out.print("<tr>");
                    out.print("<td>" + rs.getInt(1) + "</td>" + "<td>" + rs.getString(2) + "</td>" + "<td>" + rs.getString(3) + "</td>" + "<td>" + rs.getString(4) + "</td>");
                    out.print("</tr>");

                }

                out.print("</table>");

                out.print("<br>");
                out.print("<br>");
                out.println("<font color='blue'><h2>Total row(s) : " + rows + "</h2></font>");

            } else {

                out.println("<font color='red'><h2>0 rows</h2></font>");

            }
            out.print("<br>");
            out.print("<a href='student.html'><h3>HOME</h3></a>");
            out.print("</div></body>");

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

}
