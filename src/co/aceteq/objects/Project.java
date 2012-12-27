/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.aceteq.objects;

import co.aceteq.util.DateController;
import co.aceteq.util.UpdateDB;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author apoorvparijat
 */
public class Project {
    private int client_id;
    private int details_id;
    private String continued_from;
    private int order_id;
    private String project_id;
    private String project_type;
    private String date_added;
    private String tableName = "projects";
    private boolean saved;
    
    public Project(int cid,int did,int oid,String pid,String type,String date){
        this.client_id = cid;
        this.details_id = did;
        this.order_id = oid;
        this.project_id = pid;
        this.project_type = type;
        this.date_added = date;
    }
    
    public Project(int cid,int did,int oid,String pid,String type,String cf,String date){
        this.client_id = cid;
        this.details_id = did;
        this.order_id = oid;
        this.project_id = pid;
        this.project_type = type;
        this.continued_from = cf;
        this.date_added = date;
    }
    
    public boolean save(){
        Map<String,String> strData = new HashMap<String,String>();
        Map<String,Integer> intData = new HashMap<String,Integer>();
        strData.put("project_id", this.project_id);
        strData.put("continued_from", this.continued_from);
        strData.put("type", this.project_type);
        strData.put("date_added", this.date_added);
        intData.put("client_id", this.client_id);
        intData.put("details_id",this.details_id);
        intData.put("order_id", this.order_id);
        UpdateDB.insert(this.tableName, strData, intData);
        this.saved = true;
        return this.saved;
    }
}
