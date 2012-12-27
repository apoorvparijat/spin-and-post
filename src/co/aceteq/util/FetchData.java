/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.aceteq.util;

import co.aceteq.objects.Objects;
import java.sql.ResultSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 *
 * @author apoorvparijat
 */
public class FetchData {

    private static DBConnection pool = Objects.getPool();
    public boolean fieldExists(String tablename, Map<String, String> stringData, Map<String, Integer> intData) {
        boolean exists = true;
        
        return exists;
    }
    
    public static ResultSet getRow(String tableName,String [] toFetch, Map<String,String> strCondition,Map<String,Integer> intCondition){
        ResultSet s = null;
        String toFetchColumn = "*";
        if(toFetch != null){
            toFetchColumn = "";
            for(String x : toFetch){
                toFetchColumn += x + ",";
            }
            toFetchColumn = toFetchColumn.substring(0, toFetchColumn.length()-1);
        }
        String condition = "";
        if(strCondition != null){
            Iterator<Entry<String, String>> entries = strCondition.entrySet().iterator();
            Entry<String,String> x;
            while(entries.hasNext()){
                x = entries.next();
                condition += x.getKey() + " = '" + x.getValue() + "' AND ";
            }
        }
        if(intCondition != null){
            Iterator<Entry<String, Integer>> entries = intCondition.entrySet().iterator();
            Entry<String,Integer> x;
            while(entries.hasNext()){
                x = entries.next();
                condition += x.getKey() + " = " + x.getValue() + " AND ";
            }
            
        }
        
        condition = condition.equals("") ? "" : condition.substring(0, condition.length()-4);
        String query = "";
        if(!condition.equals(""))
           query = "SELECT " + toFetchColumn + " FROM " + tableName + " WHERE " + condition;
        else
            query = "SELECT " + toFetchColumn + " FROM " + tableName;
        Debugger.display(query,20);
        s = pool.getResult(query);
        return s;
    }
    
    public static ResultSet getRow(String tableName,String [] toFetch, String condition){
        ResultSet s = null;
        String toFetchColumn = "*";
        if(toFetch != null){
            toFetchColumn = "";
            for(String x : toFetch){
                toFetchColumn += x + ",";
            }
            toFetchColumn = toFetchColumn.substring(0, toFetchColumn.length()-1);
        }
        
        String query = "";
        if(!condition.equals(""))
           query = "SELECT " + toFetchColumn + " FROM " + tableName + " WHERE " + condition;
        else
            query = "SELECT " + toFetchColumn + " FROM " + tableName;
        Debugger.display(query,20);
        s = pool.getResult(query);
        return s;
    }
}
