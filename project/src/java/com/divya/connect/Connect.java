package com.divya.connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Connect {
    
    private static String url = "jdbc:mysql://localhost:3306/institute?user=root&password=welcome";
    private static boolean isInitialized;
    private static Connection con;
    
    public static Connection connect() throws Exception {
        
        if (!isInitialized) {
            
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url);
            isInitialized = true;
            
        }
        
        return con;
        
    }
    
    public static void close(Connection con) throws Exception {
        
        con.close();
        
    }
    
    public static void close(Statement s) throws Exception {
        
        s.close();
        
    }
    
    public static void close(ResultSet rs) throws Exception {
        
        rs.close();
        
    }
    

}
