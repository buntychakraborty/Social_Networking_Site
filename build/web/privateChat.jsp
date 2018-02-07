
<%@page import="com.sns.dao.PrivateChatDAOImpl"%>
<%@page import="com.sns.dao.PrivateChatDAO"%>
<%@page import="com.sns.model.PrivateChatBean"%>
<%@page import="com.sns.dao.ChatDAOImpl"%>
<%@page import="com.sns.dao.ChatDAO"%>
<%@page import="com.sns.model.ChatBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% String fname=(String)session.getAttribute("name"); %>
<% String targetId=request.getParameter("id");%>
<%session.setAttribute("r_id",targetId);%>
<%String senderId=(String)session.getAttribute("id");%>
<!doctype html>
<html>
<head>
  <title>ChatRoom</title>
<link rel="stylesheet" href="styles/chat_style.css"/>
<script>
function ajax(){
var req =new XMLHttpRequest();
req.onreadystatechange=function(){
  if(req.readyState==4&&req.status==200){
  document.getElementById('chat').innerHTML=req.responseText;}
}


req.open('GET','privateChatMediator.jsp','true');
req.send();}
setInterval(function(){ajax()},1000);
</script>
</head>
<body onload="ajax();">
     <br/>
  <div id="container">
    <div id="chat_box">
<div id="chat" >
    
</div>
    </div>
     
    <form action="#" method="post">
      <input type="text" name="name"  required="" value=<%=fname%> disabled /></br>
<textarea name="msg" placeholder="Enter your message" required=""></textarea>
<input type="submit" name="submit" value="Send "/>

    </form>
<%
String name=request.getParameter("name");
String message=request.getParameter("msg");
PrivateChatBean bean=new PrivateChatBean();
bean.setMessage(message);
bean.setName(fname);
bean.setUserId(senderId);
bean.setrId(targetId);
PrivateChatDAO dao=new PrivateChatDAOImpl();
int status=dao.insertChat(bean);
if(status==1){
    
}

%>


  </div>

</body>
</html>

