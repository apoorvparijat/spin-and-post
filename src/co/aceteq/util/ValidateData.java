/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.aceteq.util;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author apoorv
 */
public class ValidateData{
    
    private static DBConnection pool;

    public static boolean validatePassword(String pass,String repass)
    {
        boolean valid = true;
        if(!pass.equals(repass))
            valid = false;
        else if(pass.trim().split("\\s+").length > 1)
            valid = false;
        Debugger.display("Validate password returns " +valid, 20);
        return valid;
    }
    

    public static boolean validateEmail(String email)
    {
        boolean valid = true;
        String trimmed = email.trim();
        if(trimmed.split("@").length > 2)
            valid = false;
        else if(trimmed.split("\\.").length > 4)
               valid = false;
        Debugger.display("Validate email returns : "  + valid, 20);
        return valid;
    }
      
    public static boolean fieldExists(String table,String fieldName,String fieldValue)
    {
        boolean exists = false;
        String query = "Select * from "+table.trim()+" where "+fieldName.trim() +"='" + fieldValue.trim()+"'";
        Debugger.display(query, 20);
        ResultSet rs = pool.getResult(query);
        try {
            if(rs.next())
                exists = true;
        } catch (SQLException ex) {
            Debugger.display("Error in resultSet from fieldExists function "+ ex.getMessage(),  2);
        }
        Debugger.display("fieldExists returns : " + exists, 10);
        return exists;
    }
    
    public static void setPool(DBConnection db)
    {
        ValidateData.pool = db;
    }
}
