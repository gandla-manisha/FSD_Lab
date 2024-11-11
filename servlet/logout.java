/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 *
 * @author ADMIN
 */
public class logout extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
             HttpSession session = request.getSession(false);
            
            if (session != null) {
                // Retrieve session attributes: name and start time
                String name = (String) session.getAttribute("name");
                Date startTime = (Date) session.getAttribute("startTime");
                
                // Get session creation and last accessed times
                long creationTime = session.getCreationTime();
                long lastAccessedTime = session.getLastAccessedTime();
                
                // Calculate session duration in seconds
                long duration = (new Date().getTime() - startTime.getTime()) / 1000;
                
                // Format the session times using SimpleDateFormat
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                String creationTimeStr = sdf.format(new Date(creationTime));
                String lastAccessedTimeStr = sdf.format(new Date(lastAccessedTime));

                // Invalidate the session (logout user)
                session.invalidate();

                // Output logout information
                out.println("<html>");
                out.println("<head><title>Logout</title></head>");
                out.println("<body>");
                out.println("<h1>Thank you, " + name + "!</h1>");
                out.println("<p>You spent " + duration + " seconds on the site.</p>");
                out.println("<div>Session Creation Time: " + creationTimeStr + "</div>");
                out.println("<div>Last Accessed Time: " + lastAccessedTimeStr + "</div>");
                out.println("</body>");
                out.println("</html>");
            } else {
                // If no session exists, prompt user to log in
                out.println("<html><body>");
                out.println("<h2>No active session. Please log in first.</h2>");
                out.println("<a href='login'>Login</a>");
                out.println("</body></html>");
            }
        }
    }
        
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
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
     *
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
     *
     * @return a String containing servlet description
     */
    
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}