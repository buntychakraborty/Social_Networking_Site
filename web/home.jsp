

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
                            <p><strong><a href="gallery.jsp" style="font-size:18px">Photos</a></strong> <img src="images/gallery.ico" alt="image" height="18" width="18">
                            


                        
                        </div>
                            <hr>
                            <div id='mention'><center><b><h3>Upcoming Birthdays</h3></b></center></br>
                              <% for(UserBean bday:list2){%>
                            
                            <p><strong><a href="#" style="font-size:18px"><%=bday.getUserFname()%></a></strong> <img src="images/gallery.ico" alt="image" height="18" width="18">
                            
<%}%>

                        
                        </div>
                            <hr>
                        <div style="float:center; padding-right:100px;"> <script src="http://www.clocklink.com/embed.js"></script> <script type="text/javascript" language="JavaScript">obj=new Object;obj.clockfile="0018-silver.swf";obj.TimeZone="India_Bangalore"; obj.width=150;obj.height=150;obj.wmode="transparent";showClock(obj);</script></div>
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
    line-style:none; "><center><h2 style="font-family: Courier New;">Create a Post</h2></center></li>



</ul>
                        </div> <br/>
                        <br/> <br/> 
                        <textarea cols="69" rows="11" name="content" placeholder="What's on your mind?"></textarea></br>
                        </br>
                        <select name="feeling">
                            <option value="Angry">Feeling</option>
                            <option value="Angry">Angry</option>
                            <option value="Cool">Cool</option>
                            <option value="Lovely">Lovely</option>
                             <option value="Sleepy">Sleepy</option>
                            <option value="Awesome">Awesome</option>
                            <option value="Wonderful">Wonderful</option>
                            <option value="Amazing">Amazing</option>
                        </select>
                        <input type="file"  name="image1"/>

                        <input type="submit" style="width:135px;
                               height:35px;
                               border-radius:4px/4px;
                               background:#142170;
                               font-size:15px;
                               outline:none;
                               color:white;" value="Post To Timeline" name="sub"/>
                    </form>

                    </br>

                    <h3></h3></br>

                    <% for (UserPostBean post : list) {
                            UserBean b1 = UserDAOImpl.getUser(post.getUserId());
                            UserImage img1=new UserImage();
                            img1=UserImageDAOImpl.selectImage(post.getUserId());
                            String imgName1=img1.getUserImage();
                            String post_uname = b1.getUserFname();
                            String post_id=post.getPostId();
                            int x=PostLikeDAOImpl.countLike(post_id);
                            
                    %>

                    <div id='posts' style='width:600px;
                         padding:10px;
                         line-height:20px;
                         background:white;
                         width:500px;
                         text-align:justify;'>
                        <p><img src='user_images/<%=imgName1%>' width='40' height='40' style='float:left; padding-top:2px; ;padding-right:8px;'></p>
                        <h4><a href='user_profile.jsp?u_id=<%=id%>' style='text-decoration:none;'><%=post_uname%> is feeling <% out.println(post.getFeeling());   %></a></h4>
                        <h4><% out.println(post.getPostContent());%></h4>
                        <h4>

                            <!--<img src='user_images/post_images/$post_image' width='400' height=400' style='float:center; padding-top:2px; ;padding-right:8px;'>-->
                            <%=x%> People Like this
                        </h4>

                        <form  action='single.jsp?post_id=<%out.println(post.getPostId());%>' method='POST'>


                            <button  style='float:right; background:#142170; color:white;' class='button'> Comment</button></form>
                            <div><button  name='like' class='button' style="background:#142170; color:white;"> <a href="like.jsp?type=article&p_id=<%out.println(post.getPostId());%>" style="text-decoration: none;color:white;">Like</a></button></div>

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