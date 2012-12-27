/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.aceteq.objects;

import co.aceteq.util.DBConnection;
import co.aceteq.util.DateController;
import co.aceteq.util.Debugger;
import co.aceteq.util.FetchData;
import co.aceteq.util.UpdateDB;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author apoorvparijat
 */
public class Order{
    
    private static DBConnection pool = Objects.getPool();
    
    private int client_id;
    private String date_added;
    private int order_id;
    private String tableName = "orders";
    private boolean saved;
    public Order(int cid){
        client_id = cid;
        order_id = 0;
        date_added = DateController.getTimeForDB();
        saved = false;
    }
    
    public boolean save(){
        Map <String,Integer> intData = new HashMap<String,Integer>();
        intData.put("client_id", client_id);
        Map <String,String> stringData = new HashMap<String,String>();
        stringData.put("date_added", date_added);
        UpdateDB.insert(this.tableName, stringData, intData);
        saved = true;
        return saved;
    }
    
    public int getID()
    {
        int i = 0;
        if(!saved){
            return i;
        }
        if(order_id != 0)
        {
            return order_id;
        }else{
            Map<String,Integer> intData = new HashMap<String,Integer>();
            intData.put("client_id", this.client_id);
            Map<String,String> strData = new HashMap<String,String>();
            strData.put("date_added", this.date_added);
            String [] toFetch = {"order_id"};
            Debugger.display("Called getID. Calling getRow", 20);
            ResultSet rs = FetchData.getRow(this.tableName, toFetch, strData, intData);
            try{
                if(rs.next()){
                    this.order_id = rs.getInt("order_id");
                    i = this.order_id;
                }
            }catch(Exception e){
                Debugger.display("Error fetching ID of the Order : " + e.getMessage(), 2);
            }
        }
        return i;
    }
    
    public String getDate()
    {
        return this.date_added;
    }
}
