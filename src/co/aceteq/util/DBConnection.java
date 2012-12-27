/*
 * 
 */
package co.aceteq.util;


import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author apoorv
 */
public class DBConnection  {
    
    
    private String user;
    private String pass;
    private String db;
    private String connURL;
    private BoneCP connectionPool;
    //initializes the DBConnection object with username and password to be used.
    
    
    public DBConnection(String username, String password,String db)
    {
        
            this.user = username;
            this.pass = password;
            this.db = db;
            connectionPool = null;
            Connection connection = null;
            try {
                    // load the database driver (make sure this is in your classpath!)
                
                    Class.forName("com.mysql.jdbc.Driver");
            } catch (Exception e) {
                Debugger.display("Can't load class.", 20);
                    e.printStackTrace();
                    return;
            }

                    // setup the connection pool BoneCPConfig config = new com.jolbox.bonecp.BoneCPConfig();
                    BoneCPConfig config = new BoneCPConfig();
                    if(db == "")
                        db = "spinandpost";
                    config.setJdbcUrl("jdbc:mysql://localhost/"+ db); // jdbc url specific to your database, eg jdbc:mysql://127.0.0.1/yourdb
                    config.setUsername(user); 
                    config.setPassword(pass);
                    config.setMinConnectionsPerPartition(5);
                    config.setMaxConnectionsPerPartition(10);
                    config.setPartitionCount(1);
                    try{
                    connectionPool = new BoneCP(config); // setup the connection pool

                    connection = connectionPool.getConnection(); // fetch a connection

                    if (connection != null){
                            System.out.println("Connection successful!");
                            connection.close();
                    } 
                    }catch (SQLException ex){
                        Debugger.display("Error creating pool : " + ex.getMessage(), 20);
                    }
                    
    }

    public DBConnection() {
        this.user = "root";
            this.pass = "shonen";
            this.db = "spinandpost";
            connectionPool = null;
            Connection connection = null;
            try {
                    // load the database driver (make sure this is in your classpath!)
                
                    Class.forName("com.mysql.jdbc.Driver");
            } catch (Exception e) {
                Debugger.display("Can't load class.", 20);
                    e.printStackTrace();
                    return;
            }

                    // setup the connection pool BoneCPConfig config = new com.jolbox.bonecp.BoneCPConfig();
        BoneCPConfig config = new BoneCPConfig();
        config.setJdbcUrl("jdbc:mysql://localhost/"+ db); // jdbc url specific to your database, eg jdbc:mysql://127.0.0.1/yourdb
        config.setUsername(user); 
        config.setPassword(pass);
        config.setMinConnectionsPerPartition(5);
        config.setMaxConnectionsPerPartition(10);
        config.setPartitionCount(1);
        try{
        connectionPool = new BoneCP(config); // setup the connection pool

        connection = connectionPool.getConnection(); // fetch a connection

        if (connection != null){
                System.out.println("Connection successful!");
                connection.close();
        } 
        }catch (SQLException ex){
            Debugger.display("Error creating pool : " + ex.getMessage(), 20);
        }
    }

    public Connection getConnection()
    {
        Connection con = null;
        try {
            con =  (Connection) connectionPool.getConnection();
            Debugger.display("Get Connection Called : " + con, 10);
        } catch (SQLException ex) {
            Debugger.display("Error getting connection from pool : " + ex.getMessage(), 10);
        }
        return con;
    }
    
    public PreparedStatement getPreparedStatement(String template)
    {
        
        java.sql.PreparedStatement ps =  null;
        try {
            java.sql.Connection con = connectionPool.getConnection();
            if(con != null)
                ps = (java.sql.PreparedStatement)con.prepareStatement(template);
            else
                Debugger.display("Connection variable is null : ", 10);
            con.close();
        } catch (SQLException ex) {
            Debugger.display("Error getting connectino from pool : " + ex.getMessage(), 10);
        }
        return (PreparedStatement) ps;
    }
     
    
    public boolean executeQuery(PreparedStatement ps)
    {
        Connection con = null;
        boolean result = false;
        try {
            con = (Connection) ps.getConnection();
            con.setAutoCommit(false);
            ps.execute();
            con.commit();
            result = true;
            con.close();
        } catch (SQLException ex) {
            try {
                Debugger.display("Error executing PreparedStatement : " + ex.getMessage(), 1);
                con.rollback();
                Debugger.display("Rollback Successful.", 20);
            } catch (SQLException ex1) {
                Debugger.display("Rollback failed : " +ex1.getMessage() , 2);
            }
            
        }
        return result;
    }


    public boolean executeQuery(String query)
    {
        Connection con = null;
        boolean flag = false;
        try
        {
            con = this.getConnection();
            Statement s = con.createStatement();
            con.setAutoCommit(false);
            boolean execute = s.execute(query);
            flag = execute;
            Debugger.display("executeQuery(String) returned : " + execute, 10);
            con.commit();
            con.close();
        }catch(SQLException e)
        {
            try {
                con.rollback();
            } catch (SQLException ex) {
                Debugger.display("Rollback after executing Statement failed. :  " + ex.getMessage(),20);
            }
            Debugger.display("Error executing Statement : " + e.getMessage(), 20);
        }
        return flag;
    }


    public ResultSet getResult(PreparedStatement ps) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public ResultSet getResult(String query)
    {
        ResultSet s = null;
        try {
            Connection con = this.getConnection();
            Statement stmt = (Statement) con.createStatement();
            Debugger.display("Executing query : " + query, 10);
            s = stmt.executeQuery(query);

        } catch (SQLException ex) {
            Debugger.display("Error executing statement from getResult(String query) : " + ex.getMessage(), 10);
        }
        return s;
    }
    
}
