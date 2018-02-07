


<%@page import="com.sns.dao.UserImageDAOImpl"%>
<%@page import="com.sns.model.UserImage"%>
<%@page import="com.sns.model.FriendBean"%>
<%@page import="com.sns.dao.FriendDAOImpl"%>
<%@page import="com.sns.dao.FriendDAO"%>
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
<%ArrayList<FriendBean> luser=new ArrayList<FriendBean>();
FriendDAOImpl daoUser=new FriendDAOImpl();
luser=daoUser.fetchAllRecord(id);
%>
<%UserImage img=new UserImage();%>
<%img=UserImageDAOImpl.selectImage(id);  
String imgName=img.getUserImage();
session.setAttribute("img1",imgName);

%>
<%if(username!=null){ %>


<!doctype html>
<html>
    <head>
        <title>
            Friend List
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
                        <li><a href="members.jsp">Members</a></li>
                        <li><a href="chatroom.jsp">Chat Room</a></li>
                        <li><a href="user_timeline.jsp">Timeline</a></li>

                    </ul>
                    <form action="SearchUser" method="post" id="form1" style="padding-right:10px;">
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
                        <%if(gender.equals("Male") &&imgName==null){%>
                        <center><img src='images/default.png' width='200'/></center></br>
                        <%}%>
                        <%if(gender.equals("Female") &&imgName==null){%>
                        <center><img src='images/lady.png' width='200'/></center></br>
                        <%}%>
                        <%if( imgName!=null){%>
                        <center><img src='user_images/<%=imgName%>' width='200'/></center></br>
                            <%}%>


                        <div id='mention' >
                            <p><strong><a href="Profile.jsp">Profile</a></strong> <img src="images/img.jpg" alt="image" height="20" width="20">
                            <p><strong>First Name:</strong><%=fname%></p> 
                            <p><strong>Country:</strong>India</p>
                            <p><strong>Login Date:</strong><%=llogin%></p>



                            <p><a style='font-size:17px;' href='user_timeline.jsp?id=<%=id%>'>Timeline</a>&nbsp;<img src="images/edit.ico" alt="image" height="19" width="19"></p>
                            <p><a style='font-size:17px;' href='edit_profile.jsp?id=<%=id%>'>Settings&nbsp;<img src="images/settings.png" alt="image" height="18" width="18"></a></p>
                        </div>
                        <hr>
                        <div id='mention' >
                            <p><a href="games/" >Games</a> <img src="images/games.ico" alt="image" height="18" width="18">
                            <p><strong><a href="#" style="font-size:18px">Groups</a></strong> <img src="images/group.jpeg" alt="image" height="18" width="18">
                            <p><strong><a href="#" style="font-size:18px">Photos</a></strong> <img src="images/gallery.ico" alt="image" height="18" width="18">
                            


                        
                        </div>
                            <hr>
                        <div style="float:center; padding-right:100px;"> <script src="http://www.clocklink.com/embed.js"></script> <script type="text/javascript" language="JavaScript">obj=new Object;obj.clockfile="0018-silver.swf";obj.TimeZone="India_Bangalore"; obj.width=150;obj.height=150;obj.wmode="transparent";showClock(obj);</script></div>
                    </div>
                </div>

                <div id="content_timeline">

                    <h3> My Friend List</h3><br/>
                  <%  for(FriendBean ob:luser){
                      
                      String u_id=ob.getFriendUid();
                      
                    
                   UserImage img2=new UserImage();
               img2=UserImageDAOImpl.selectImage(u_id);
                      String image=img2.getUserImage();
                      
                  %>
           
                  <a href='user_profile.jsp?u_id=<%=u_id   %>'>
                      <img src="user_images/<%=image%>" height="50" width="50" title="<%out.print(ob.getfName());%>"/>
                      
                      
                  </a>
                  <%}%>
                </div>


            </div>

            </div>
                </body>
                </html>
<%  }%>
<%if(username==null){
    response.sendRedirect("index.jsp");
}%>

