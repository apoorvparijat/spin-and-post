/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.aceteq.ajax;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author apoorvparijat
 */
public class AjaxAction {
    
    protected String progress;
    protected String responseData;
    public HttpServletRequest formData;
    
    public AjaxAction(HttpServletRequest fd){
        this.formData = fd;
    }
    
    public String getResponseData(){
        return responseData;
    }
    
    public String getProgress(){
        return progress;
    }
    
    public void execute(){
        
    }
    
}
