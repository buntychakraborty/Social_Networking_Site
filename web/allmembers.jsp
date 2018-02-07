


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
<%ArrayList<UserBean> luser=new ArrayList<UserBean>();
UserDAO daoUser=new UserDAOImpl();
luser=daoUser.fetchAllRecord();
%>



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
                        <li><a href="adminpage.jsp">Home</a></li>
                        <li><a href="members.jsp">Members</a></li>
                        

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
                       


                        <div id='mention' >
                            <p><strong><a href="adminpage.jsp">Profile</a></strong> <img src="images/img.jpg" alt="image" height="20" width="20">
                            <p><strong>First Name:</strong>Admin Singh</p> 
                            <p><strong>Country:</strong>India</p>
                            



                            <p><a style='font-size:17px;' href='user_timeline.jsp?id=<%=id%>'>Timeline</a>&nbsp;<img src="images/edit.ico" alt="image" height="19" width="19"></p>
                            <p><a style='font-size:17px;' href='edit_profile.jsp?id=<%=id%>'>Settings&nbsp;<img src="images/settings.png" alt="image" height="18" width="18"></a></p>
                            
                        </div>
                        <hr>
                      
                            <hr>
                        <div style="float:center; padding-right:100px;"> <script src="http://www.clocklink.com/embed.js"></script> <script type="text/javascript" language="JavaScript">obj=new Object;obj.clockfile="0018-silver.swf";obj.TimeZone="India_Bangalore"; obj.width=150;obj.height=150;obj.wmode="transparent";showClock(obj);</script></div>
                    </div>
                </div>

                <div id="content_timeline">

                    <h3> All Registered Users on this site</h3><br/>
                  <%  for(UserBean ob:luser){
                      String ugender=ob.getUserGender();
                      String logStat=ob.getLoginStatus();
                     String logPath=null; 
                      String path=null;
               
                      if(ugender.equals("Male")){
                          path="images/default.png";
                      }
                      if(ugender.equals("Female")){
                          path="images/lady.png";     
                      }
                      if(logStat.equals("online")){
                          logPath="images/dot.png";
                          
                      }
               if(logStat.equals("offline")){
                          logPath="images/red.png";
                          
                      }
                  %>
           
                  <a href='adminuser_profile.jsp?u_id=<%=ob.getUserId()%>'>
                      <img src="<%out.println(path);%>" height="50" width="50" title="<%out.print(ob.getUserFname());%>"/>
                      
                      <img src=<%=logPath%> height="10" width="10" title="<%=ob.getLoginStatus()%>"/>
                  </a>
                  <%}%>
                </div>


            </div>

            </div>
                </body>
                </html>



