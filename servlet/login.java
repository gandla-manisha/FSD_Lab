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
public class login extends HttpServlet {

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
           String name = request.getParameter("name");
        
        if (name != null && !name.trim().isEmpty()) {
            // Create or get the session
            HttpSession session = request.getSession();
            
            // Store the 'name' and current time in the session
            session.setAttribute("name", name);
            session.setAttribute("startTime", new Date());  // Store current date-time
            
            // Get session creation time and last accessed time
            long creationTime = session.getCreationTime();
            long lastAccessedTime = session.getLastAccessedTime();

            // Format the times using SimpleDateFormat
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");  // Time format
            String creationTimeStr = sdf.format(new Date(creationTime));
            String lastAccessedTimeStr = sdf.format(new Date(lastAccessedTime));

            // Display the greeting and session times
            out.println("<html>");
            out.println("<head><title>Welcome, " + name + "</title></head>");
            out.println("<body>");
            out.println("<h1>Hello, " + name + "!</h1>");

            // Display session start time in top-right corner using SimpleDateFormat
            out.println("<div style='position: absolute; top: 10px; right: 10px;'>Start Time: " + sdf.format(session.getAttribute("startTime")) + "</div>");
            
            // Display session creation time and last accessed time
            out.println("<div>Session Creation Time: " + creationTimeStr + "</div>");
            out.println("<div>Last Accessed Time: " + lastAccessedTimeStr + "</div>");
            
            // Display logout button
            out.println("<form action='logout' method='post'><button type='submit'>Logout</button></form>");
            out.println("</body>");
            out.println("</html>");
        } else {
            // If no name provided, ask the user for their name
            out.println("<html><body>");
            out.println("<h2>Please enter your name:</h2>");
            out.println("<form action='login' method='post'>");
            out.println("<input type='text' name='name' required>");
            out.println("<button type='submit'>Submit</button>");
            out.println("</form>");
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
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}