package com.sns.dao;

import com.sns.model.UserBean;
import com.sns.model.UserImage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserImageDAOImpl implements UserImageDAO {
    
    @Override
    public void insertImage(UserImage img) {
        Connection conn = null;
        PreparedStatement pst = null;
        int status = 0;
        int size = 0;
        
        try {
            conn = DBConnect.getConnection();
            
            pst = conn.prepareStatement("insert into user_images(user_id,user_image) values(?,?) ");
            size = UserImageDAOImpl.checkImageStatus(img.getUserId());
            pst.setString(1, img.getUserId());
            pst.setString(2, img.getUserImage());
            
            if (size == 0) {
                int rowCount = pst.executeUpdate();
                if (rowCount > 0) {
                    status = 1;
                } else {
                    status = 0;
                }
            }
            if (size >0) {
                UserImageDAOImpl.updateImage(img);
            }
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static UserImage selectImage(String id) {
        Connection conn = null;
        Statement pst = null;
        UserImage bean = new UserImage();
        String query = "SELECT * FROM user_images where user_id = '" + id + "'";
        //  String sql="SELECT * FROM USER_REGISTRATION WHERE USER_EMAIL='uname'";
        try {
            
            conn = DBConnect.getConnection();
            pst = conn.createStatement();
            
            ResultSet rs = pst.executeQuery(query);
            if (rs.next()) {
                bean.setUserImage(rs.getString("user_image"));
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return bean;
    }
    
    public static void insertImage1(UserImage img) {
        Connection conn = null;
        PreparedStatement pst = null;
        int status = 0;
        
        try {
            conn = DBConnect.getConnection();
            
            pst = conn.prepareStatement("insert into gallery_images(user_id,image) values(?,?) ");
            
            pst.setString(1, img.getUserId());
            
            pst.setString(2, img.getUserImage());
            
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
    }
    
    public static ArrayList<UserImage> fetchAllRecord(String id) {
        ArrayList<UserImage> list = new ArrayList<UserImage>();
        Connection conn = null;
        Statement pst = null;
        
        String query = "SELECT * FROM gallery_images where user_id = '" + id + "' order by id desc limit 9";
        //  String sql="SELECT * FROM USER_REGISTRATION WHERE USER_EMAIL='uname'";
        try {
            
            conn = DBConnect.getConnection();
            pst = conn.createStatement();
            
            ResultSet rs = pst.executeQuery(query);
            while (rs.next()) {
                UserImage bean = new UserImage();
                bean.setUserId(rs.getString("user_id"));
                bean.setUserImage(rs.getString("image"));
                list.add(bean);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
        
    }
    
    public static int checkImageStatus(String id) {
        Connection conn = null;
        ResultSet rst = null;
        Statement st = null;
        int size = 0;
        String query = "SELECT count(*) as rowcount FROM user_images where user_id = '" + id + "'";
        try {
            
            conn = DBConnect.getConnection();
            
            st = conn.createStatement();
            rst = st.executeQuery(query);
            rst.next();
            size = rst.getInt("rowcount");
            System.out.println("MyTable has " + size + " row(s).");
            
        } catch (Exception ex) {
        }
        
        return size;
    }
    
    public static void updateImage(UserImage img) {
        Connection conn = null;
        PreparedStatement pst = null;
        String query = "UPDATE user_images set user_id='" + img.getUserId() + "',user_image='" + img.getUserImage() + "'";
        try {
            
            conn = DBConnect.getConnection();
            
            pst = conn.prepareStatement(query);
            
            int i1 = pst.executeUpdate();
            
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            DBConnect.closeOracleConnection(conn);
            DBConnect.closePreparedStatementConnection(pst);
            
        }
        
    }
    public static ArrayList<UserImage> fetchAllRecords(String id) {
        ArrayList<UserImage> list = new ArrayList<UserImage>();
        Connection conn = null;
        Statement pst = null;
        
        String query = "SELECT * FROM gallery_images where user_id = '" + id + "' order by id desc ";
        //  String sql="SELECT * FROM USER_REGISTRATION WHERE USER_EMAIL='uname'";
        try {
            
            conn = DBConnect.getConnection();
            pst = conn.createStatement();
            
            ResultSet rs = pst.executeQuery(query);
            while (rs.next()) {
                UserImage bean = new UserImage();
                bean.setUserId(rs.getString("user_id"));
                bean.setUserImage(rs.getString("image"));
                list.add(bean);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
        
    }
}
