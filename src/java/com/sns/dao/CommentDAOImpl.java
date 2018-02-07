package com.sns.dao;

import com.sns.model.CommentBean;
import com.sns.model.UserPostBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CommentDAOImpl implements CommentDAO {

    @Override
    public int insertComment(CommentBean cBean) {

        Connection conn = null;
        PreparedStatement pst = null;
        int status = 0;
        try {
            conn = DBConnect.getConnection();

            pst = conn.prepareStatement("insert into comments(post_id,user_id,comment,date,comment_author) values(?,?,?,NOW(),?)");
            pst.setString(1, cBean.getPostId());
            pst.setString(2, cBean.getUserId());
            pst.setString(3, cBean.getComment());
            pst.setString(4, cBean.getCommentAuthor());

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
    public ArrayList<CommentBean> getAllComment(String id) {
        ArrayList<CommentBean> list = new ArrayList<CommentBean>();
        Connection conn = null;
        Statement pst = null;
        String query = "SELECT * FROM comments where  post_id = '" + id + "'";

        try {

            conn = DBConnect.getConnection();
            pst = conn.createStatement();

            ResultSet rs = pst.executeQuery(query);
            while (rs.next()) {
                CommentBean bean = new CommentBean();
                bean.setPostId(rs.getString("post_id"));
                bean.setComment(rs.getString("comment"));
                bean.setCommentAuthor(rs.getString("comment_author"));
                bean.setUserId(rs.getString("user_id"));
                bean.setDate(rs.getString("date"));
                //bean.setCommentId(rs.getString("user_id"));

                list.add(bean);
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;

    }

}
