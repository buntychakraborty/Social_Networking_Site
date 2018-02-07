
<%@page import="com.sns.model.PrivateChatBean"%>
<%@page import="com.sns.model.ChatBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.sns.dao.PrivateChatDAOImpl"%>
<%@page import="com.sns.dao.PrivateChatDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%String rId=(String)session.getAttribute("r_id");%>

<%String senderId=(String)session.getAttribute("id");%>
<%
ArrayList<PrivateChatBean> list=null;
%>
<%
PrivateChatDAO dao=new PrivateChatDAOImpl();
list=dao.selectChat(senderId,rId);

 %>
 <%for(PrivateChatBean bean:list){ %>
<div id="chat_data">
  <span style="color:green;"><% out.println(bean.getName()); %>:</span>
    <span style="color:brown;"><% out.println(bean.getMessage()); %></span>
    <span style="color:black; float:right;"><% out.println(bean.getDate()); %></span>

</div>
<%}%>
