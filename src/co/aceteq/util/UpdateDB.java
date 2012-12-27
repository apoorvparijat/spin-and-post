/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.aceteq.util;

import co.aceteq.objects.Objects;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 *
 * @author apoorvparijat
 */
public class UpdateDB{

    private static DBConnection pool = Objects.getPool();
    
    /*
     * Usage : boolean insert() 
     * 
     * Parameter 1 = tableName
     * Parameter 2 = Map<String,String> stringData
     *                          First String is the column name that has the type "VARCHAR" or "LONG TEXT" or similar
     *                          Second String is the value
     * Parameter 3 = Map <String, Integer > intData
     *                          First String is the columne name that has the type "Integer"
     *                          Second String is the value
     */
    public static boolean insert(String tableName, Map<String, String> stringData, Map<String, Integer> intData) {
        boolean success = false;
        String columns = "";
        String values = "";
        if(stringData != null){
            Iterator<Entry<String, String>> entries = stringData.entrySet().iterator();
            Entry<String,String> x;
            while(entries.hasNext()){
                x = entries.next();
                columns += x.getKey() + ",";
                values += "\"" + x.getValue() + "\",";
            }
        }
        if(intData != null){
            Iterator<Entry<String,Integer>> intEntries = intData.entrySet().iterator();
            Entry<String,Integer> y;
            while(intEntries.hasNext()){
                y = intEntries.next();
                columns += y.getKey() + ",";
                values += y.getValue() + ",";
            }
        }
        String query = "INSERT INTO " + tableName + "(" + columns.substring(0, columns.length()-1) + ") values (" + values.substring(0,values.length()-1) + ")";
        Debugger.display(query,10);
        success = pool.executeQuery(query);
        return success;
    }


    public static boolean update(String tableName, Map<String, String> stringData, Map<String, Integer> intData,String whereField,String whereValue) {
        boolean success = false;
        String toSet = "";
        if(stringData != null){
            Iterator<Entry<String, String>> entries = stringData.entrySet().iterator();
            Entry<String,String> x;
            while(entries.hasNext()){
                x = entries.next();
                toSet = x.getKey() + " = '" + x.getValue() + "',";
            }
        }
        if(intData != null){
            Iterator<Entry<String,Integer>> intEntries = intData.entrySet().iterator();
            Entry<String,Integer> y;
            while(intEntries.hasNext()){
                y = intEntries.next();
                toSet = y.getKey() + " = '" + y.getValue() + "',";
            }
        }
        String query = "UPDATE " + tableName + " SET " + toSet.substring(0, toSet.length()-1) + " WHERE " + whereField + " = '" + whereValue + "'";
        Debugger.display("Update query : " + query, 10);
        success = pool.executeQuery(query);
        return success;
    }


    public boolean delete(String table, Map<String, String> stringData, Map<String, Integer> intData) {
        boolean success = false;
        return success;
    }
    
    
    public static boolean isPoolSet()
    {
        boolean yes = pool == null ? false : true;
        return yes;
    }
}
