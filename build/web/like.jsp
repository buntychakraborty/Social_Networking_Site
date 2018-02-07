

<%@page import="com.sns.dao.PostLikeDAO"%>
<%@page import="com.sns.dao.PostLikeDAOImpl"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
       <% 
       String id=request.getParameter("p_id");
       String type=request.getParameter("type");
       switch(type){
           case "article":
           PostLikeDAO dao=new PostLikeDAOImpl();
           dao.postLike(id, (String)session.getAttribute("id"));
           response.sendRedirect("home.jsp");
           break;
       }
       %>
        
    </body>
</html>
