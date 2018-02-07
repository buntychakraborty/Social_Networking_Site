

       
<%@page import="com.sns.dao.UserPostDAOImpl"%>
<%@page import="com.sns.dao.UserPostDAO"%>
<%@page import="com.sns.model.UserPostBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.sns.model.UserBean"%>
<%@page import="com.sns.dao.UserDAOImpl"%>
<%@page import="com.sns.dao.UserDAO"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%UserBean bean=new UserBean();%>


<%  ArrayList<UserBean> list=new ArrayList<UserBean>();
    list=(ArrayList<UserBean>)request.getAttribute("bean");
    
    
%>
<%String username = (String) session.getAttribute("email");%>
<% UserDAO dao = new UserDAOImpl();
    bean = dao.selectUser(username);
    String fname = bean.getUserFname();
    String lname = bean.getUserLname();
    String llogin = bean.getLlogin();
    String id = bean.getUserId();
    String gender = bean.getUserGender();
    session.setAttribute("id", id);
    session.setAttribute("name", fname);

%>


<%if(username!=null){ %>



<!doctype html>
<html>
<head>
<title>
Home Page
</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!--<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://www.w3schools.com/lib/w3-theme-blue-grey.css">
<link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Open+Sans'>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">-->
<link rel="stylesheet" href="styles/home_style.css" media="all">
</head>
<body style="background-color: lightgray;">



<div class="container" >
<div id="head_wrap">
<!-- header Starts -->
<div id="header">

<ul id="menu">
<li><a href="home.jsp">Home</a></li>
<li><a href="user_profile.jsp">Profile</a></li>
<li><a href="chatroom.jsp">Chat Room</a></li>
<li><a href="user_timeline.jsp">Timeline</a></li>

</ul>
<form action="SearchUser" method="get" id="form1" style="padding-right:10px;">
<input type="text" name="search_users" placeholder="Search VKonnect..." required/>
<button name="login" style="width:85px;
	height:30px;
	border-radius:4px/4px;
	background:#142170;
	font-size:15px;
	outline:none;
	color:white;">Search</button>
       
</form>
  
 

</div>
</div>

<div class="content" >

<div id="user_timeline" >
<div id="user_details" >
    


<div id='mention' >
    <p><strong><a href="Profile.jsp">Profile</a></strong> <img src="images/img.jpg" alt="image" height="20" width="20">
                            <p><strong>First Name:</strong><%=fname%></p> 
                            <p><strong>Country:</strong>India</p>
                            <p><strong>Login Date:</strong><%=llogin%></p>



                            <p><a style='font-size:17px;' href='user_timeline.jsp?id=<%=id%>'>Timeline</a>&nbsp;<img src="images/edit.ico" alt="image" height="19" width="19"></p>
                            <p><a style='font-size:17px;' href='edit_profile.jsp?id=<%=id%>'>Settings&nbsp;<img src="images/settings.png" alt="image" height="18" width="18"></a></p>
    
    
    </div>
 <hr>

</div>
</div>

<div id="content_timeline">

<form action="PostServlet" method="post" id="f" >

    <div style="background-color: white; width:515px; padding-top: 30px; text-align: left; line-height:35px; padding-left: 20px;
padding:0;
margin:0;
float:left; text-decoration: none;" >
       
        <ul >
<li style="display:inline;
line-style:none; "><a href="home.jsp">User For You!</a></li>



</ul>
    </div> <br/>
    <br/> <br/> 


</br>

<h3></h3></br>

<% for(UserBean bean1:list){
   
%>

<div id='posts' style='width:600px;
padding:10px;
line-height:20px;
background:white;
width:500px;
text-align:justify;'>
<p><img src='images/lady.png' width='40' height='40' style='float:left; padding-top:2px; ;padding-right:8px;'></p>
<h4><a href='user_profile.jsp?u_id=<% out.println(bean1.getUserId());%>' style='text-decoration:none;'><% out.print(bean1.getUserFname()+ " " +bean1.getUserLname());   %> </a></h4>
<h4></h4>
<h4>

<!--<img src='user_images/post_images/$post_image' width='400' height=400' style='float:center; padding-top:2px; ;padding-right:8px;'>-->

</h4>

<form  action='single.jsp?post_id=' method='POST'>
<% out.print(bean1.getUserLname());    %><br/>

</form>

</div>
</br>

<%}%>














</div>


</div>

<div>
</body>
</html>

<%  }%>
<%if(username==null){
    response.sendRedirect("index.jsp");
}%>
