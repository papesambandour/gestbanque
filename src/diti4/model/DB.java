package diti4.model;

import java.sql.*;

/**
 * Created by Pape Ndour on 28/10/2017.
 */
public class DB {

    public static Statement statement() throws SQLException
    {
        String URL = "jdbc:mysql://localhost:3306/projet1fx" ;
        String LOGIN = "root" ;
        String PASSWOORD = "root";
        Statement stmt = null;
        Connection conn = null;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection(URL,LOGIN,PASSWOORD);
            stmt = conn.createStatement() ;
        }
        catch (Exception e) {
            System.out.print("SQL EXCEPTION");
            e.printStackTrace();
            System.exit(0);
        }


        return stmt ;
    }

}
