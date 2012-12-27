/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.aceteq.ajax.actions;

import co.aceteq.ajax.AjaxAction;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author apoorvparijat
 */
public class GetProgress extends AjaxAction{
    
    public GetProgress(HttpServletRequest fd){
        super(fd);
    }
    
    @Override
    public void execute(){
        HttpSession hs = super.formData.getSession();
        AjaxAction action = (AjaxAction) hs.getAttribute("ajax_"+super.formData.getParameter("actionToTrack"));
        super.responseData = action.getProgress()+"";
        //System.out.println(responseData);
    }
    
}