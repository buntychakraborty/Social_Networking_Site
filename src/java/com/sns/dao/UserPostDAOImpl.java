
package com.sns.dao;

import com.sns.model.UserPostBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserPostDAOImpl implements UserPostDAO {

    private static final String INSERT_POST="insert into posts(user_id,feeling,post_content,post_date,post_image) values(?,?,?,CURDATE(),?)";
    
    
    
    
    
    @Override
    public int insertPost(UserPostBean post) {
       Connection conn = null;
        PreparedStatement pst = null;
        int status = 0;
        try {
            conn = DBConnect.getConnection();
            
            pst = conn.prepareStatement(INSERT_POST);
            pst.setString(1, post.getUserId());
            pst.setString(2, post.getFeeling());
            pst.setString(3, post.getPostContent());
            pst.setString(4, post.getPostImage());
            
           System.out.print(post.getFeeling());
            
            int rowCount;
            rowCount = pst.executeUpdate();
            if (rowCount > 0) {
                status = 1;
            } else {
                status = 0;
            }
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        return status;
    }

    @Override
    public ArrayList<UserPostBean> getAllPost() {
        ArrayList<UserPostBean> list=new ArrayList<UserPostBean>();
        Connection conn = null;
        Statement pst = null;
       
        String query = "SELECT * FROM posts order by post_id desc ";
        try {
            
            conn = DBConnect.getConnection();
            pst = conn.createStatement();
            
            ResultSet rs = pst.executeQuery(query);
            while (rs.next()) {
                 UserPostBean bean = new UserPostBean();
                bean.setPostId(rs.getString("post_id"));
                bean.setFeeling(rs.getString("feeling"));
                bean.setPostContent(rs.getString("post_content"));
                bean.setPostImage(rs.getString("post_image"));
                bean.setUserId(rs.getString("user_id"));
                
               
                list.add(bean);
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        return list;
        
        
        
    }
    
    public ArrayList<UserPostBean> getAllPost(String id) {
        ArrayList<UserPostBean> list=new ArrayList<UserPostBean>();
        Connection conn = null;
        Statement pst = null;
       String query = "SELECT * FROM posts where  user_id = '" + id+ "'";
       
        try {
            
            conn = DBConnect.getConnection();
            pst = conn.createStatement();
            
            ResultSet rs = pst.executeQuery(query);
            while (rs.next()) {
                 UserPostBean bean = new UserPostBean();
                bean.setPostId(rs.getString("post_id"));
                bean.setFeeling(rs.getString("feeling"));
                bean.setPostContent(rs.getString("post_content"));
                bean.setPostImage(rs.getString("post_image"));
                bean.setUserId(rs.getString("user_id"));
                
               
                list.add(bean);
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        return list;
        
        
        
    }
    public static UserPostBean getAllPostById(int id) {
       
        Connection conn = null;
        Statement pst = null;
       String query = "SELECT * FROM posts where  post_id = '" + id+ "'";
       UserPostBean bean = new UserPostBean();
        try {
            
            conn = DBConnect.getConnection();
            pst = conn.createStatement();
            
            ResultSet rs = pst.executeQuery(query);
            if (rs.next()) {
                 
                bean.setPostId(rs.getString("post_id"));
                bean.setFeeling(rs.getString("feeling"));
                bean.setPostContent(rs.getString("post_content"));
                bean.setPostImage(rs.getString("post_image"));
                bean.setUserId(rs.getString("user_id"));
                
               
                
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        return bean;
        
        
        
    }
    }
    
    

