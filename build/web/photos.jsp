
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
<%String u_id=request.getParameter("u_id");
UserBean b=new UserBean();
b=UserDAOImpl.getUserByUid(u_id);
String friendName=b.getUserFname();
session.setAttribute("friend",friendName);

%>


<%UserBean bean=new UserBean();%>

<%String username=(String)session.getAttribute("email");%>
<% UserDAO dao=new UserDAOImpl();
bean=dao.selectUser(username);
String fname=bean.getUserFname();
String lname=bean.getUserLname();
String llogin=bean.getLlogin();
String id=bean.getUserId();   //id of the logged in user
String bday=bean.getUserBirthday();
String gender=bean.getUserGender();
session.setAttribute("id", id);
session.setAttribute("name",fname);

%>
<%  ArrayList<UserPostBean> list=new ArrayList<UserPostBean>();
    UserPostDAO daoPost=new UserPostDAOImpl();
   list=daoPost.getAllPost(u_id);
  
    
%>
<%UserImage img=new UserImage();%>
<%img=UserImageDAOImpl.selectImage(u_id);  
String imgName=img.getUserImage();


%>
<%
ArrayList<UserImage> list5=new ArrayList<UserImage>();
%>
<%list5=UserImageDAOImpl.fetchAllRecords(u_id);  


%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <!-- This file has been downloaded from Bootsnipp.com. Enjoy! -->
    <title>Gallery</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet">
    <style type="text/css">
        
.gallery-title
{
    font-size: 36px;
    color: #42B32F;
    text-align: center;
    font-weight: 500;
    margin-bottom: 70px;
}
.gallery-title:after {
    content: "";
    position: absolute;
    width: 7.5%;
    left: 46.5%;
    height: 45px;
    border-bottom: 1px solid #5e5e5e;
}
.filter-button
{
    font-size: 18px;
    border: 1px solid #42B32F;
    border-radius: 5px;
    text-align: center;
    color: #42B32F;
    margin-bottom: 30px;

}
.filter-button:hover
{
    font-size: 18px;
    border: 1px solid #42B32F;
    border-radius: 5px;
    text-align: center;
    color: #ffffff;
    background-color: #42B32F;

}
.btn-default:active .filter-button:active
{
    background-color: #42B32F;
    color: white;
}

.port-image
{
    width: 100%;
}

.gallery_product
{
    margin-bottom: 30px;
}

    </style>
    <script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
</head>
<body>
 <div class="container">
        <div class="row">
        <div class="gallery col-lg-12 col-md-12 col-sm-12 col-xs-12">
            <h1 class="gallery-title">Gallery</h1>
        </div>

        
        <br/>
<%for(UserImage img2:list5){%>
      

            <div class="gallery_product col-lg-4 col-md-4 col-sm-4 col-xs-6 filter hdpe">
                
                <img height="450px" width="365px" src="user_images/<%=img2.getUserImage()%>" title="<%=img2.getUserImage()%> " >
              
            </div>
  <%}%>
            
        </div>
    </div>
</section>

<script type="text/javascript">
$(document).ready(function(){

    $(".filter-button").click(function(){
        var value = $(this).attr('data-filter');
        
        if(value == "all")
        {
            //$('.filter').removeClass('hidden');
            $('.filter').show('1000');
        }
        else
        {
//            $('.filter[filter-item="'+value+'"]').removeClass('hidden');
//            $(".filter").not('.filter[filter-item="'+value+'"]').addClass('hidden');
            $(".filter").not('.'+value).hide('3000');
            $('.filter').filter('.'+value).show('3000');
            
        }
    });
    
    if ($(".filter-button").removeClass("active")) {
$(this).removeClass("active");
}
$(this).addClass("active");

});
</script>
</body>
</html>
