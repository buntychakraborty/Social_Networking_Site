<%@page import="com.sns.dao.UserImageDAOImpl"%>
<%@page import="com.sns.model.UserImage"%>
<%@page import="com.sns.dao.PostLikeDAOImpl"%>
<%@page import="com.sns.dao.UserPostDAOImpl"%>
<%@page import="com.sns.dao.UserPostDAO"%>
<%@page import="com.sns.model.UserPostBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.sns.model.UserBean"%>
<%@page import="com.sns.dao.UserDAOImpl"%>
<%@page import="com.sns.dao.UserDAO"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%UserBean bean = new UserBean();%>

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
<%  ArrayList<UserPostBean> list = new ArrayList<UserPostBean>();
    UserPostDAO daoPost = new UserPostDAOImpl();
    list = daoPost.getAllPost();

    
%>

<%  ArrayList<UserBean> list2 = new ArrayList<UserBean>();
   
    list2 = UserDAOImpl.getRecentBirthday();

    
%>
<%
ArrayList<UserImage> list5=new ArrayList<UserImage>();
%>
<%list5=UserImageDAOImpl.fetchAllRecord(id);  


%>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <!-- This file has been downloaded from Bootsnipp.com. Enjoy! -->
    <title></title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet">
    <style type="text/css">
        @import url(http://fonts.googleapis.com/css?family=Anaheim);

*{
  margin: 0;
  padding: 0;
  outline: none;
  border: none;
    box-sizing: border-box;
}
*:before,
*:after{
	box-sizing: border-box;
}
html,
body{
	min-height: 100%;
}
body{
	background-image: radial-gradient(mintcream 0%, lightgray 100%);
}
h1{
	display: table;
	margin: 5% auto 0;
	text-transform: uppercase;
	font-family: 'Anaheim', sans-serif;
	font-size: 4em;
	font-weight: 400;
	text-shadow: 0 1px white, 0 2px black;
}
.container{
	margin: 4% auto;
	width: 210px;
	height: 140px;
	position: relative;
	perspective: 1000px;
}
#carousel{
	width: 100%;
	height: 100%;
	position: absolute;
	transform-style: preserve-3d;
	animation: rotation 20s infinite linear;
}
#carousel:hover{
	animation-play-state: paused;
}
#carousel figure{
	display: block;
	position: absolute;
	width: 90%;
	height: 50%px;
	left: 10px;
	top: 10px;
	background: black;
	overflow: hidden;
	border: solid 5px black;
}
#carousel figure:nth-child(1){transform: rotateY(0deg) translateZ(288px);}
#carousel figure:nth-child(2) { transform: rotateY(40deg) translateZ(288px);}
#carousel figure:nth-child(3) { transform: rotateY(80deg) translateZ(288px);}
#carousel figure:nth-child(4) { transform: rotateY(120deg) translateZ(288px);}
#carousel figure:nth-child(5) { transform: rotateY(160deg) translateZ(288px);}
#carousel figure:nth-child(6) { transform: rotateY(200deg) translateZ(288px);}
#carousel figure:nth-child(7) { transform: rotateY(240deg) translateZ(288px);}
#carousel figure:nth-child(8) { transform: rotateY(280deg) translateZ(288px);}
#carousel figure:nth-child(9) { transform: rotateY(320deg) translateZ(288px);}

img{
	-webkit-filter: grayscale(1);
	cursor: pointer;
	transition: all .5s ease;
}
img:hover{
	-webkit-filter: grayscale(0);
  transform: scale(1.2,1.2);
}

@keyframes rotation{
	from{
		transform: rotateY(0deg);
	}
	to{
		transform: rotateY(360deg);
	}
}
    </style>
    <script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
</head>
<body>                         
                         
<p><a style='font-size:17px;' href='home.jsp?id=<%=id%>'><button style='background:#142170; color:white; padding:2px; padding-right: 2px;'>Home</button></a></p>
<h1>Image Gallery of <%=fname%></h1>
  <div class="container">
    	<div id="carousel">
			<%for(UserImage img:list5){%>
                        <figure><img src="user_images/<%=img.getUserImage()  %>" alt="" height="250" width="250"></figure>
                        <%}%>
                       
                        
		</div>
	</div>
<script type="text/javascript">

</script>

</body>
</html>
