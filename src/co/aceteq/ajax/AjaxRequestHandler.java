/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.aceteq.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author apoorvparijat
 */
public class AjaxRequestHandler extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession hs = request.getSession();
        
        try {
              try {
                  
           Class cls = Class.forName("co.aceteq.ajax.actions."+request.getParameter("ajaxAction"));
           Class partypes[] = new Class[1];
            System.out.println("Creating object for : " + request.getParameter("ajaxAction"));
            partypes[0] = javax.servlet.http.HttpServletRequest.class;
            Constructor ct 
              = cls.getConstructor(partypes);
            Object arglist[] = new Object[1];
            arglist[0] = request;
            
            // Creating object of the class.
            
            Object ajaxObject =  ct.newInstance(arglist);
            
            
            Method meth = cls.getMethod("execute");
            
            // Calling execute method of the action
            
            Object methInvoked = meth.invoke(ajaxObject);
            
            // Calling getResponseData of the action
            
            meth = cls.getMethod("getResponseData");
            methInvoked = meth.invoke(ajaxObject);
            String responseData = (String)methInvoked;
            
            // saving ajax object in session 
            
            
            hs.setAttribute("ajax_"+request.getParameter("ajaxAction"), ajaxObject);
            out.println(responseData);
         }
         catch (Throwable e) {
             e.printStackTrace();
            System.err.println(e.getMessage());
         }
        } finally {            
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
