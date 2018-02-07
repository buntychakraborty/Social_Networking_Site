<%String img1=(String)session.getAttribute("img1");%>



<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Profile Picture </title>
<style type="text/css">
   
  .button
{
    background: #142170 url(../img/overlay.png) repeat-x;
    padding: 6px 8px 6px;
    color: white;
    text-decoration: none;
    -moz-border-radius: 5px;
    -webkit-border-radius: 5px;
    -moz-box-shadow: 0 1px 3px rgba(0,0,0,0.5);
    -webkit-box-shadow: 0 1px 3px rgba(0,0,0,0.5);
    text-shadow: 0 -1px 1px rgba(0,0,0,0.25);
    cursor: pointer;
    float:center;
    font-size:14px;
    margin:10px;
}
</style>
</head>
<body bgcolor="lightgray">

 
  <br/>
  <p align="center">
     
     <center><img src='user_images/<%=img1%>' width='200' style="border-radius:10px;"/></center></br>
     
 </p>

  <div align="center">
   <div style="background-color: #efeee0; width: 450px;">
   <div style="color: red; font-size: 24px;">
		<%
    		if(request.getAttribute("msg") != null)
    		{
    			out.println(request.getAttribute("msg"));
        		out.println("<br>");
        	}
		%>
	</div>
  </div>
  </div>

  <div align="center">
   <div style="background-color: lightgray; width: 450px;">
 <form action="UploadImage" method="post" enctype="multipart/form-data">
    
    
    <div style="color: yellow; font-size: 18px;" >
        
    <input type="file" name="fileName" class="button">
    <input type="submit" value="Upload" class="button">
    </div>


</form>
</div>
</div>
<br />
</body>
</html>