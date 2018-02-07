

       
<%@page import="com.sns.dao.CommentDAOImpl"%>
<%@page import="com.sns.dao.CommentDAO"%>
<%@page import="com.sns.model.CommentBean"%>
<%@page import="com.sns.dao.UserPostDAOImpl"%>
<%@page import="com.sns.dao.UserPostDAO"%>
<%@page import="com.sns.model.UserPostBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.sns.model.UserBean"%>
<%@page import="com.sns.dao.UserDAOImpl"%>
<%@page import="com.sns.dao.UserDAO"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%UserBean bean=new UserBean();%>

<%String username=(String)session.getAttribute("email");%>
<% UserDAO dao=new UserDAOImpl();
bean=dao.selectUser(username);
String fname=bean.getUserFname();
String lname=bean.getUserLname();
String llogin=bean.getLlogin();
String id=bean.getUserId();
String gender=bean.getUserGender();
session.setAttribute("id", id);
session.setAttribute("name",fname);

%>
<%String postId=request.getParameter("post_id");
 session.setAttribute("pid",postId);
%>

<%  ArrayList<UserPostBean> list=new ArrayList<UserPostBean>();
    UserPostDAO daoPost=new UserPostDAOImpl();
   UserPostBean bean2=UserPostDAOImpl.getAllPostById(Integer.parseInt(postId));
   
String postContent=bean2.getPostContent();
String feelings=bean2.getFeeling();
UserBean b1=UserDAOImpl.getUser(bean2.getUserId());
    String post_uname=b1.getUserFname();
    session.setAttribute("commentAuthor", post_uname);
%>


<%  ArrayList<CommentBean> listComment=new ArrayList<CommentBean>();
    CommentDAO daoComment=new CommentDAOImpl();
   listComment=daoComment.getAllComment(postId);
  
    
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
<li><a href="members.jsp">Profile</a></li>
<li><a href="chatroom.jsp">Chat Room</a></li>
<li><a href="user_timeline.jsp">Timeline</a></li>

</ul>
<form action="#" method="get" id="form1" style="padding-right:10px;">
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
    <%if(gender.equals("Female")) {   %>
<center><img src='images/lady.png' width='200'/></center></br>
<%}%>
<%if(gender.equals("Male")) {   %>
<center><img src='images/default.png' width='200'/></center></br>
<%}%>


<div id='mention' >
    <p><strong><a href="Profile.jsp">Profile</a></strong> <img src="images/img.jpg" alt="image" height="20" width="20">
   <p><strong>First Name:</strong><%=fname%></p> 
   <p><strong>Country:</strong>India</p>
<p><strong>Login Date:</strong><%=llogin %></p>
	
   
 
 <p><a style='font-size:17px;' href='user_timeline.jsp?id=<%=id%>'>Timeline</a>&nbsp;<img src="images/edit.ico" alt="image" height="19" width="19"></p>
 <p><a style='font-size:17px;' href='UpdateEmployee?id=<%=id%>'>Settings&nbsp;<img src="images/settings.png" alt="image" height="18" width="18"></a></p>
<p><a style='font-size:17px;' href='DeleteServlet?id=<%=id%>'><button>Delete Profile</button></a></p>
 <p><a style='font-size:17px;' href='LogoutServlet'><button>Logout</button></a></p></div>
 <hr>

</div>
</div>

<div id="content_timeline">


<div id='posts' style='width:500px;
padding:10px;
line-height:20px;
background:white;

text-align:justify;'>
<p><img src='images/default.png' width='40' height='40' style='float:left; padding-top:2px; ;padding-right:8px;'></p>
<h4><a href='user_profile.jsp' style='text-decoration:none;'><%=post_uname%> is feeling <%=feelings%></a></h4>
<h4><%=postContent%></h4>
<h4>



</h4>

</div>
</br>
<%for(CommentBean beanComment:listComment){%>



<div id='comments' style='width:500px;
padding:10px;
line-height:20px;
background:white;

text-align:justify;'>
    <h3><% out.print(beanComment.getCommentAuthor());%></h3><i>said at</i> <%out.print(beanComment.getDate()); %>
<p><%out.println(beanComment.getComment());%></p>
</div>
</br>
<%}%>
<form action='CommentServlet' method='post' id='reply'>
<textarea cols='71' rows='5' name='comment' required placeholder='Comment...'></textarea></br></br>
<button style='background-color: #4CAF50; border: none;
    color: white;
    padding: 10px 32px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 16px;
float:center;'name='reply'>Comment</button>

</form>


</div>


</div>




</div>


</div>

<div>
</body>
</html>


<%  }%>
<%if(username==null){
    response.sendRedirect("index.jsp");
}%>