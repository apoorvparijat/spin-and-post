/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spinner;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import spinneralgorithm.PatternMap;
import spinneralgorithm.SpinnerAlgorithm;

/**
 *
 * @author apoorv
 */
public class ReceiveArticle extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
   
    @Override
    public void init()
    {
        /*
        db = (DBConnection)getServletContext().getAttribute("dbPool");
        if(db == null){
            db = new DBConnection();
            getServletContext().setAttribute("dbPool", db);
            Objects.setPool(db);
        }
         * 
         */
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            //out.println(request.getParameter("title"));
            //out.println(request.getParameter("article"));
            int count = Integer.parseInt(request.getParameter("count"));
            String title = request.getParameter("title");
            if(title.length() >5000){
                out.print("Title too long.");
                return;
            }
            String article = request.getParameter("article");
            System.out.println(article.length());
            if(article.length() >10000){
                out.print("Article too long.");
                return;
            }
            article = article.replaceAll("\n", "<br />");
            SpinnerAlgorithm st = new SpinnerAlgorithm(title);
            String [] spunTitles = st.getSpun(count);
            SpinnerAlgorithm sa = new SpinnerAlgorithm(article);
            String [] spunArticles = sa.getSpun(count);
            
            HttpSession hs = request.getSession();
            hs.setAttribute("spunArticles", spunArticles);
            hs.setAttribute("spunTitles", spunTitles);
            hs.setAttribute("count", request.getParameter("count"));
            hs.setAttribute("tags", request.getParameter("tags"));
            hs.setAttribute("category", request.getParameter("category"));
            PatternMap pm = new PatternMap(sa.getPatternMap());
            PatternMap pm2 = new PatternMap(st.getPatternMap());
                RequestDispatcher dispatcher = request.getRequestDispatcher("SpunArticles.jsp");
                dispatcher.forward(request, response);
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
