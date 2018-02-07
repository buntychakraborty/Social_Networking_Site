
package com.sns.dao;

import com.sns.model.FriendBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bunty
 */
public class FriendDAOImpl implements FriendDAO {

    @Override
    public int addFriend(FriendBean friend) {
        
      
        ResultSet st = null;
        Connection conn = null;
    
        PreparedStatement pst = null;
        int status = 0;
        
        try {
            conn = DBConnect.getConnection();

            pst = conn.prepareStatement("insert into friends(user_id,f_user_id,friend_name) values(?,?,?) ");
            
            pst.setString(1, friend.getuId());
            pst.setString(2, friend.getFriendUid());
            pst.setString(3, friend.getfName());
           
            
        
            int rowCount = pst.executeUpdate();
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
    public int addFriendToFriend(FriendBean friend) {
        
      
        ResultSet st = null;
        Connection conn = null;
    
        PreparedStatement pst = null;
        int status = 0;
        
        try {
            conn = DBConnect.getConnection();

            pst = conn.prepareStatement("insert into friends(user_id,f_user_id,friend_name) values(?,?,?) ");
            
            pst.setString(1, friend.getFriendUid());
            pst.setString(2, friend.getuId());
            pst.setString(3, friend.getfName());
           
            
        
            int rowCount = pst.executeUpdate();
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
    public ArrayList<FriendBean> fetchAllRecord(String uid) {
        ArrayList<FriendBean> list=new ArrayList<FriendBean>();
        Connection conn = null;
        Statement pst = null;
       
      String query = "SELECT * FROM friends where user_id = '" + uid + "'";
        //  String sql="SELECT * FROM USER_REGISTRATION WHERE USER_EMAIL='uname'";
        try {
            
            conn = DBConnect.getConnection();
            pst = conn.createStatement();
            
            ResultSet rs = pst.executeQuery(query);
            while (rs.next()) {
                 FriendBean bean = new FriendBean();
                 bean.setfName(rs.getString("friend_name"));
                 bean.setFriendUid(rs.getString("f_user_id"));
                list.add(bean);
            }
 
        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
        
        
    }
}
