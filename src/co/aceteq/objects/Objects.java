/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.aceteq.objects;

import co.aceteq.util.DBConnection;

/**
 *
 * @author apoorvparijat
 */
public class Objects {
    
    
    private static DBConnection pool;
    
    public static void setPool(DBConnection db){
        if(pool == null)
            pool = db;
    }
    
    public static DBConnection getPool(){
        return pool;
    }
}