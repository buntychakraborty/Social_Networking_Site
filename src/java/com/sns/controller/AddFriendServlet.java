package com.sns.controller;

import com.sns.dao.FriendDAO;
import com.sns.dao.FriendDAOImpl;
import com.sns.model.FriendBean;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Bunty
 */
@WebServlet(name = "AddFriendServlet", urlPatterns = {"/AddFriendServlet"})
public class AddFriendServlet extends HttpServlet {

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
            String friendId = request.getParameter("id");
            HttpSession session = request.getSession(false);
            String uid = (String) session.getAttribute("id");
            String friendName = (String) session.getAttribute("friend");
            FriendBean bean = new FriendBean();

            bean.setuId(uid);
            bean.setFriendUid(friendId);
            bean.setfName(friendName);
            FriendDAO dao = new FriendDAOImpl();
            FriendDAOImpl dao1 = new FriendDAOImpl();
            int status = dao.addFriend(bean);

            if (status > 0) {
               FriendBean bean1 = new FriendBean();
                 bean1.setuId(uid);
            bean1.setFriendUid(friendId);
            bean1.setfName((String)session.getAttribute("name"));
           int status1= dao1.addFriendToFriend(bean1);
           if(status>0){
                RequestDispatcher rd = request.getRequestDispatcher("user_profile.jsp?u_id=" + uid);
                rd.forward(request, response);
            }
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
