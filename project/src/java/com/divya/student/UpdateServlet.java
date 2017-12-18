package com.divya.student;

import com.divya.connect.Connect;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {

            Class.forName("com.mysql.jdbc.Driver");
            Connection con = Connect.connect();

            int id = Integer.parseInt(request.getParameter("id"));

            PreparedStatement ps = con.prepareStatement("select id from student");
            ResultSet rs = ps.executeQuery();

            boolean flag = false;

            while (rs.next()) {
                if (id == rs.getInt(1)) {
                    flag = true;
                    break;
                }
            }

            if (flag) {
                
                ps = con.prepareStatement("select id, name, course, city from student where id = ?");

                String name = request.getParameter("name");
                String course = request.getParameter("course");
                String city = request.getParameter("city");

                ps.setInt(1, id);

                rs = ps.executeQuery();

                rs.next();

                if (name == null || name.isEmpty()) {
                    name = rs.getString(2);
                }
                if (course == null || course.isEmpty()) {
                    course = rs.getString(3);
                }
                if (city == null || city.isEmpty()) {
                    city = rs.getString(4);
                }

                Connect.close(rs);
                Connect.close(ps);

                Statement s = con.createStatement();

                int count = 0;

                count = s.executeUpdate("update student set name = " + "'" + name + "'" + ", city = " + "'" + city + "'" + ", course = " + "'" + course + "'" + " where id = " + request.getParameter("id"));

                out.print("<div align = 'center'>");
                out.print("<br>");
                out.print("<br>");
                out.print("<br>");

                if (count > 0) {

                    out.print("<font color = 'blue'><h1>1 row updated</h1></font>");

                } else {

                    out.print("<font color = 'red'><h1>0 row updated</h1></font>");

                }
            }
            
            else {
                out.print("<div align='center'");
                out.print("<font color='red'><h2>No row set for id = " + id + "</h2></font>");
            }
            out.print("<br>");
            out.print("<a href='student.html'><h3>HOME</h3></a>");
            out.print("</div>");

        } catch (Exception e) {

            e.printStackTrace();

        }
    }
}
