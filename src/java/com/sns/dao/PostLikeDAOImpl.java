package com.sns.dao;

import com.sns.model.FriendBean;
import com.sns.model.UserBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PostLikeDAOImpl implements PostLikeDAO {

    @Override
    public void postLike(String p_Id, String u_id) {
        int postId = Integer.parseInt(p_Id);
        int uId = Integer.parseInt(u_id);
        ResultSet st = null;
        Connection conn = null;
        PreparedStatement pst = null;
        int status = 0;
        int size = 0;
        try {
            conn = DBConnect.getConnection();

            pst = conn.prepareStatement("insert into post_likes(user_id,post_id) values(?,?) ");
            pst.setString(1, u_id);
            pst.setString(2, p_Id);
            size = PostLikeDAOImpl.checkLikeStatus(p_Id, u_id);

            if (size == 0) {
                int rowCount = pst.executeUpdate();
                if (rowCount > 0) {
                    status = 1;
                } else {
                    status = 0;
                }
            }
            if (size == 1) {
                PostLikeDAOImpl.removeLike(p_Id, u_id);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public static int checkLikeStatus(String pid, String uid) {
        Connection conn = null;
        ResultSet rst = null;
        Statement st = null;
        int size = 0;
        String query = "SELECT count(*) as rowcount FROM post_likes where post_id = '" + pid + "' and  user_id='" + uid + "'";
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

    public static int countLike(String post_id) {

        Connection conn = null;
        ResultSet rst = null;
        Statement st = null;
        int size = 0;
        String query = "SELECT count(*) as rowcount FROM post_likes where post_id = '" + post_id + "' ";
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

    public static void removeLike(String post_id, String uid) {
        Connection conn = null;
        PreparedStatement pst = null;
        String query = "delete from post_likes where post_id='" + post_id + "' and user_id='" + uid + "' ";
        try {
            conn = DBConnect.getConnection();
            pst = conn.prepareStatement(query);
            int rowCount = pst.executeUpdate();
        } catch (Exception ex) {
        }
    }

}
