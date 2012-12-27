/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.aceteq.util;


import co.aceteq.objects.Objects;
import co.aceteq.objects.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 *
 * @author apoorvparijat
 */
public class Authenticate {
    
    public static DBConnection pool = Objects.getPool();
    
    public static User checkLogin(String tableName,String user,String pass){
        User us = null;
        String username = "";
        String name = "";
        int id = 0;
        boolean valid = false;
        String query = "SELECT username,id,name FROM " + tableName + "_login WHERE password = '" + pass + "'";
        if(tableName.equals("employee")){
                query = "SELECT username,role,id,name FROM " + tableName + "_login WHERE password = '" + pass + "'";
            }
        ResultSet rs;
        try{
            rs = pool.getResult(query);
            if(rs.next()){
                username = rs.getString("username");
                id = rs.getInt("id");
                name = rs.getString("name");
            }
        
        valid = user.equals(username) ? true : false;
        String session_id = "";
        if(valid)
        {
            Date d = new Date();
            Long s = d.getTime();
            session_id = Encryption.getSHA(s.toString() + user);
            us = new User(user,id,tableName,session_id,name);
        }else{
            Debugger.display("not found", 20);
        }
        }catch(SQLException e){
            Debugger.display(e.getMessage(), 2);
        }
        return us;
    }
    
    
}