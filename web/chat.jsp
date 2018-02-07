
<%@page import="com.sns.model.ChatBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.sns.dao.ChatDAOImpl"%>
<%@page import="com.sns.dao.ChatDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
ArrayList<ChatBean> list=null;
%>
<%
ChatDAO dao=new ChatDAOImpl();
list=dao.selectChat();
 %>
 <%for(ChatBean bean:list){ %>
<div id="chat_data">
  <span style="color:green;"><% out.println(bean.getName()); %>:</span>
    <span style="color:brown;"><% out.println(bean.getMessage()); %></span>
    <span style="color:black; float:right;"><% out.println(bean.getDate()); %></span>

</div>
<%}%>
