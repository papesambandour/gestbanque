package diti4.dao;

import java.sql.*;
import java.util.List;

public class DatabaseHelper {
   private Connection cnx ;
   private Statement stmt ;
   private PreparedStatement prstmt ;
   private static DatabaseHelper db ;

   private DatabaseHelper()
   {
       
   }
   public static DatabaseHelper getInstance()
   {
       if(db == null)
       {
           db = new DatabaseHelper();
       }
       return db ;
   }

    private void getConnexion() throws Exception {

        String URL = "jdbc:mysql://localhost:3306/projet1fx" ;
        String LOGIN = "root" ;
        String PASSWOORD = "root";

        if(cnx == null)
        {

            try
            {
                Class.forName("com.mysql.jdbc.Driver");
                cnx = DriverManager.getConnection(URL,LOGIN,PASSWOORD);
                stmt = cnx.createStatement();
            }
            catch (Exception e) {
               throw e ;
            }
        }

        else
        {
            if(cnx.isClosed())
            {
                cnx = DriverManager.getConnection(URL,LOGIN,PASSWOORD);
                stmt = cnx.createStatement();
            }
        }
    }

    public ResultSet myExecuteQuery(String sql) throws Exception {

        try {
            getConnexion();
            return stmt.executeQuery(sql);
        }
        catch (Exception e)
        {
            throw e ;
        }
    }

    public int myExecuteUpdate(String sql) throws Exception {

        try {
            getConnexion();
            return stmt.executeUpdate(sql);
        }
        catch (Exception e)
        {
            throw e ;
        }
    }

    public void myPreparedStatement(String sql) throws Exception {
       try {
           getConnexion();
           prstmt = cnx.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
       }
        catch (Exception e)
        {
            throw e ;
        }
    }

    public void addParam(int[] indice , Object[] valeur) throws Exception {
       try{
           for(int i = 0 ; i < valeur.length ; i++)
           {
               prstmt.setObject(indice[i],valeur[i]);
           }
       }
       catch (Exception e)
       {
           throw e;
       }

    }

    public ResultSet myPrepareExecuteQuery() throws Exception
    {
        try {
            return prstmt.executeQuery();
        }
        catch (Exception e)
        {
            throw e;
        }
    }

    public int myPrepareExecuteUpdate() throws Exception
    {
        try {
            return prstmt.executeUpdate();
        }
        catch (Exception e)
        {
            throw e;
        }
    }

    public int lastInsertId() throws SQLException {
        ResultSet keys = null;
        try {
            keys = prstmt.getGeneratedKeys();
            if(keys.next())
            {
                return keys.getInt(1);
            }
            return -1 ;
        } catch (SQLException e) {
            throw e;
        }

    }

    public void beginTransaction() throws Exception
    {
        try{
            getConnexion();
            cnx.setAutoCommit(false);
        }
        catch (Exception e)
        {
            throw e ;
        }
    }

    public void endTransaction() throws Exception
    {
        try{
            getConnexion();
            cnx.setAutoCommit(true);
        }
        catch (Exception e)
        {
            throw e ;
        }
    }

    public void myCommit() throws Exception
    {
        try{
            cnx.commit();
        }
        catch (Exception e)
        {
            throw e ;
        }
    }

    public void myRollback() throws Exception
    {
        try{
            cnx.rollback();
        }
        catch (Exception e)
        {
            throw e ;
        }
    }

    public void closeConnexion() throws Exception{

        try
        {
            if(stmt != null && !stmt.isClosed())
                stmt.close();

            if(prstmt != null && !prstmt.isClosed())
                prstmt.close();
            if(cnx != null && !cnx.isClosed())
                cnx.close();

        }
        catch(Exception e){
            throw e;
        }
    }
}
