/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.aceteq.objects;

/**
 *
 * @author apoorvparijat
 */
public class User {
    
    private String username = "";
    private int user_id;
    private String user_type = "";
    private String session_id;
    private String name;
    
    public User(String u, int id,String ut,String session,String name){
        this.user_id = id;
        this.username = u;
        this.user_type = ut;
        this.session_id = session;
        this.name = name;
    }
    
    public String getUsername()
    {
        return username;
    }
    
    public String getUserType()
    {
        return user_type;
    }
    
    public String getSessionId()
    {
        return session_id;
    }
    
    public int getUserId()
    {
        return user_id;
    }
}
