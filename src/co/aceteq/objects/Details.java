/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.aceteq.objects;

import co.aceteq.util.Debugger;
import co.aceteq.util.FetchData;
import co.aceteq.util.UpdateDB;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author apoorvparijat
 */
public class Details {
    
    private int detail_id = 0;
    private String text;
    private String file_id;
    private String tableName;
    private String project_id;
    private boolean saved;
    
    public Details ()
    {
        text = "";
        file_id = "";
        this.tableName = "details";
    }
    public Details(String text,String pid)
    {
        this.text = text;
        this.project_id = pid;
        this.tableName = "details";
    }
    
    public Details(String text, String file, String pid){
        this.text = text;
        this.project_id = pid;
        this.file_id = file;
        this.tableName = "details";
    }
    
    public void setText(String t){
        this.text = t;
    }
    
    public boolean save()
    {
        Map<String,String> strData = new HashMap<String,String>();
        strData.put("text", this.text);
        strData.put("project_id", this.project_id);
        UpdateDB.insert(this.tableName, strData, null);
        this.saved = true;
        return this.saved;
    }
    
    public int getID()
    {
        
        if(detail_id != 0){
            return detail_id;
        }
        Map<String,String> strData = new HashMap<String,String>();
        strData.put("project_id", this.project_id);
        String [] toFetch = {"detail_id"};
        ResultSet rs = FetchData.getRow(this.tableName, toFetch, strData, null);
        try{
            if(rs.next()){
                this.detail_id = rs.getInt("detail_id");
            }
        }catch(SQLException e){
            Debugger.display("Error in getID of Details class " + e.getMessage(), 2);
        }
        return this.detail_id;
    }
}
