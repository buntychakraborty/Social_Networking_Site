
package com.sns.controller;

import com.sns.dao.UserDAOImpl;
import com.sns.dao.UserPostDAO;
import com.sns.dao.UserPostDAOImpl;
import com.sns.model.UserPostBean;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "PostServlet", urlPatterns = {"/PostServlet"})
public class PostServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String content=request.getParameter("content");
            String feelings=request.getParameter("feeling");
            String post_image=request.getParameter("image1");
            HttpSession session=request.getSession(false);
            
         String id1=(String)session.getAttribute("id");
        
         out.println(id1);
            UserPostBean userBean=new UserPostBean();
            userBean.setFeeling(feelings);
            userBean.setPostContent(content);
            userBean.setPostImage(post_image);
            userBean.setUserId(id1);
            UserPostDAO dao=new UserPostDAOImpl();
           int status= dao.insertPost(userBean);
           out.println(status);
            if(status==1){
               UserDAOImpl.updatePostStatus(id1);
                response.sendRedirect("home.jsp");
            
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
