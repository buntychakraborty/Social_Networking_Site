
package com.sns.controller;

import com.sns.model.UserBean;
import com.sns.model.UserService;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.oreilly.servlet.MultipartRequest;  
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpSession;
@MultipartConfig
public class RegistrationServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            //Storing the Form parameter into local String variables
           String userFname=request.getParameter("user_fname");
           String userLname=request.getParameter("user_lname");
           String userEmail=request.getParameter("user_email");
           String userPassword=request.getParameter("user_password");
           String userCountry=request.getParameter("user_country");
           String userGender=request.getParameter("user_gender");
           String userBirthday=request.getParameter("user_birthday");
           String userMobile=request.getParameter("user_mobile");
           String userImage=request.getParameter("user_image");
           
           HttpSession session=request.getSession(true);
           session.setAttribute("email", userEmail);
           
           
           
          //  
           
           
           
           
           
           
          //Creating User BEAN Object to store the form values into it
           UserBean user=new UserBean();
           user.setUserFname(userFname);
           user.setUserLname(userLname);
           user.setUserEmail(userEmail);
           user.setUserPassword(userPassword);
           user.setUserMobile(userMobile);
           user.setUserImage(userImage);
           user.setUserCountry(userCountry);
           user.setUserGender(userGender);
           user.setUserBirthday(userBirthday);
           //Passing the Bean object to Service Class for Further processing
           UserService userSer=new UserService();
           String status= userSer.register(user);
       // MultipartRequest m=new MultipartRequest(request,"C:\\Users\\Bunty\\Documents\\NetBeansProjects\\Social_Networking_Site\\web\\images"); 
        if(status.equals("Record saved successfully!")){
             
            out.print("<script>alert('Record saved successfully!');</script>"); 
           
            response.sendRedirect("home.jsp");
        }
        else{
            out.println("<script>alert('Sorry! unable to save record');</script>");
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
