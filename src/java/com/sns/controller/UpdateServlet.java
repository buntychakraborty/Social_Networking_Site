
package com.sns.controller;

import com.sns.dao.UserDAO;
import com.sns.dao.UserDAOImpl;
import com.sns.model.UserBean;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.oreilly.servlet.MultipartRequest;  
import javax.servlet.annotation.MultipartConfig;

@WebServlet(name = "UpdateServlet", urlPatterns = {"/UpdateServlet"})
@MultipartConfig
public class UpdateServlet extends HttpServlet {

  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String userFname=request.getParameter("user_fname");
           String userLname=request.getParameter("user_lname");
           String userEmail=request.getParameter("user_email");
           String userPassword=request.getParameter("user_pass");
String userIntro=request.getParameter("intro");
     
           String userGender=request.getParameter("user_gender");
      
           String userMobile=request.getParameter("user_phone");
           String userImage=request.getParameter("edit_image");
           
           HttpSession session=request.getSession(true);
           session.setAttribute("email", userEmail);
   

          //Creating User BEAN Object to store the form values into it
           UserBean user=new UserBean();
           user.setUserFname(userFname);
           user.setUserLname(userLname);
           user.setUserEmail(userEmail);
           user.setUserPassword(userPassword);
           user.setUserMobile(userMobile);
           
           user.setUserImage(userImage);

           user.setUserGender(userGender);
         user.setUserGender(userGender);
         user.setUserIntro(userIntro);
           UserDAO dao=new UserDAOImpl();
         int status=  dao.UpdateUser(user, userEmail);
        //  MultipartRequest m=new MultipartRequest(request,"C:\\Users\\Bunty\\Documents\\NetBeansProjects\\Social_Networking_Site\\web\\user_images");  
           if(status>0){
             
            out.print("<script>alert('Record updated successfully!');</script>"); 
          
            response.sendRedirect("edit_profile.jsp");
        }
        else{
            out.println("<script>alert('Sorry! unable to update record');</script>");
        }
        
        out.close();
           
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
