
<%@page import="com.sns.dao.ChatDAOImpl"%>
<%@page import="com.sns.dao.ChatDAO"%>
<%@page import="com.sns.model.ChatBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% String fname=(String)session.getAttribute("name"); %>

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


req.open('GET','chat.jsp','true');
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
ChatBean bean=new ChatBean();
bean.setMessage(message);
bean.setName(fname);
ChatDAO dao=new ChatDAOImpl();
int status=dao.insertChat(bean);
if(status==1){
    out.println("<embed loop='false' src='chat.wav' hidden='true' autoplay='true'/>");
}

%>


  </div>

</body>
</html>

