package com.sns.dao;

import com.sns.model.UserBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDAOImpl implements UserDAO {

    //Query Part
    private static final String INSERT_INTO_DATABASE = "INSERT INTO user_registration(user_fname,user_lname,user_email,user_pass,user_country,user_gender,user_birthday,user_mobile,user_image,reg_date,status,posts,last_login,login_status) values(?,?,?,?,?,?,?,?,?,CURDATE(),?,?,CURDATE(),'online')";
    private static final String SELECT_USER = "SELECT * FROM USER_REGISTRATION";

    @Override
    public int insertUser(UserBean newUser) {

        Connection conn = null;
        PreparedStatement pst = null;
        int status = 0;

        try {
            conn = DBConnect.getConnection();

            pst = conn.prepareStatement(INSERT_INTO_DATABASE);

            pst.setString(1, newUser.getUserFname());
            pst.setString(2, newUser.getUserLname());
            pst.setString(3, newUser.getUserEmail());

            pst.setString(4, newUser.getUserPassword());
            pst.setString(5, newUser.getUserCountry());
            pst.setString(6, newUser.getUserGender());
            pst.setString(7, newUser.getUserBirthday());
            pst.setString(8, newUser.getUserMobile());
            pst.setString(9, newUser.getUserImage());

            pst.setString(10, "univerified");
            pst.setString(11, "No");

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
    public boolean userLogin(String uname, String pass) {
        boolean f = false;
        Connection conn = null;
        Statement pst = null;
        try {

            conn = DBConnect.getConnection();
            pst = conn.createStatement();

            ResultSet i1 = pst.executeQuery("select * from user_registration where user_email='uname' and user_pass='pass'");
            if (i1 != null) {
                f = true;
                UserDAOImpl.updateLogin(uname);

            } else {
                f = false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return f;

    }

    public static int verifyEmail(String email) {

        int f = 0;
        Connection conn = null;
        Statement pst = null;
        try {

            conn = DBConnect.getConnection();
            pst = conn.createStatement();

            ResultSet i1 = pst.executeQuery("select * from user_registration where user_email='email'");
            if (i1 != null) {
                f = 1;
            } else {
                f = 0;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return f;
    }

    @Override
    public UserBean selectUser(String uname) {

        Connection conn = null;
        Statement pst = null;
        UserBean bean = new UserBean();
        String query = "SELECT * FROM USER_REGISTRATION where user_email = '" + uname + "'";
        //  String sql="SELECT * FROM USER_REGISTRATION WHERE USER_EMAIL='uname'";
        try {

            conn = DBConnect.getConnection();
            pst = conn.createStatement();

            ResultSet rs = pst.executeQuery(query);
            if (rs.next()) {
                bean.setUserFname(rs.getString("user_fname"));
                bean.setUserLname(rs.getString("user_lname"));
                bean.setUserEmail(rs.getString("user_email"));
                bean.setUserPassword(rs.getString("user_pass"));
                bean.setUserCountry(rs.getString("user_country"));
                bean.setUserGender(rs.getString("user_gender"));
                bean.setUserImage(rs.getString("user_image"));
                bean.setUserId(rs.getString("user_id"));
                bean.setLlogin(rs.getString("last_login"));
                bean.setUserBirthday(rs.getString("user_birthday"));
                bean.setUserMobile(rs.getString("user_mobile"));
                bean.setUserIntro(rs.getString("user_intro"));
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(uname);
        return bean;

    }

    @Override
    public ArrayList<UserBean> fetchAllRecord() {
        ArrayList<UserBean> list = new ArrayList<UserBean>();
        Connection conn = null;
        Statement pst = null;

        String query = "SELECT * FROM USER_REGISTRATION";
        //  String sql="SELECT * FROM USER_REGISTRATION WHERE USER_EMAIL='uname'";
        try {

            conn = DBConnect.getConnection();
            pst = conn.createStatement();

            ResultSet rs = pst.executeQuery(query);
            while (rs.next()) {
                UserBean bean = new UserBean();
                bean.setUserFname(rs.getString("user_fname"));
                bean.setUserLname(rs.getString("user_lname"));
                bean.setUserEmail(rs.getString("user_email"));
                bean.setUserPassword(rs.getString("user_pass"));
                bean.setUserCountry(rs.getString("user_country"));
                bean.setUserGender(rs.getString("user_gender"));
                bean.setUserImage(rs.getString("user_image"));
                bean.setUserId(rs.getString("user_id"));
                bean.setLlogin(rs.getString("last_login"));
                bean.setLoginStatus(rs.getString("login_status"));
                list.add(bean);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;

    }

    @Override
    public int deleteUser(int id) {
        int f = 0;
        Connection conn = null;
        PreparedStatement pst = null;
        String query = "DELETE FROM USER_REGISTRATION where user_id = '" + id + "'";
        try {

            conn = DBConnect.getConnection();

            pst = conn.prepareStatement(query);

            int i1 = pst.executeUpdate();

            if (i1 > 0) {
                f = 1;
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            DBConnect.closeOracleConnection(conn);
            DBConnect.closePreparedStatementConnection(pst);

        }

        return f;

    }

    public static void updateLogin(String email) {
        Connection conn = null;
        PreparedStatement pst = null;
        String query = "UPDATE USER_REGISTRATION set last_login=CURDATE(),login_status='online' where user_email = '" + email + "'";
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

    public static void updatePostStatus(String id) {

        Connection conn = null;
        PreparedStatement pst = null;
        String query = "UPDATE USER_REGISTRATION set posts='yes' where user_id = '" + id + "'";
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

    public static UserBean getUser(String uid) {

        Connection conn = null;
        Statement pst = null;
        UserBean bean = new UserBean();
        String query = "SELECT * FROM USER_REGISTRATION where posts='yes' and user_id = '" + uid + "'";
        //  String sql="SELECT * FROM USER_REGISTRATION WHERE USER_EMAIL='uname'";
        try {

            conn = DBConnect.getConnection();
            pst = conn.createStatement();

            ResultSet rs = pst.executeQuery(query);
            if (rs.next()) {
                bean.setUserFname(rs.getString("user_fname"));
                bean.setUserLname(rs.getString("user_lname"));
                bean.setUserEmail(rs.getString("user_email"));
                bean.setUserPassword(rs.getString("user_pass"));
                bean.setUserCountry(rs.getString("user_country"));
                bean.setUserGender(rs.getString("user_gender"));
                bean.setUserImage(rs.getString("user_image"));
                bean.setUserId(rs.getString("user_id"));
                bean.setLlogin(rs.getString("last_login"));

            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return bean;

    }

    public static ArrayList<UserBean> getUserByName(String uid) {
        ArrayList<UserBean> list = new ArrayList<UserBean>();
        Connection conn = null;
        Statement pst = null;
        UserBean bean = new UserBean();
        String query = "SELECT * FROM USER_REGISTRATION where  user_fname = '" + uid + "'";
        //  String sql="SELECT * FROM USER_REGISTRATION WHERE USER_EMAIL='uname'";
        try {

            conn = DBConnect.getConnection();
            pst = conn.createStatement();

            ResultSet rs = pst.executeQuery(query);
            while (rs.next()) {
                bean.setUserFname(rs.getString("user_fname"));
                bean.setUserLname(rs.getString("user_lname"));
                bean.setUserEmail(rs.getString("user_email"));
                bean.setUserPassword(rs.getString("user_pass"));
                bean.setUserCountry(rs.getString("user_country"));
                bean.setUserGender(rs.getString("user_gender"));
                bean.setUserImage(rs.getString("user_image"));
                bean.setUserId(rs.getString("user_id"));
                bean.setLlogin(rs.getString("last_login"));
                list.add(bean);
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;

    }

    @Override
    public int UpdateUser(UserBean upUser, String email) {

        Connection conn = null;
        PreparedStatement pst = null;
        int status = 0;
        String query = "UPDATE USER_REGISTRATION set user_fname=?,user_lname=?,user_email=?,user_pass=?,user_gender=?,user_mobile=?,user_image=?,user_intro=? where user_email = '" + email + "'";
        try {
            conn = DBConnect.getConnection();

            pst = conn.prepareStatement(query);

            pst.setString(1, upUser.getUserFname());
            pst.setString(2, upUser.getUserLname());
            pst.setString(3, upUser.getUserEmail());

            pst.setString(4, upUser.getUserPassword());

            pst.setString(5, upUser.getUserGender());

            pst.setString(6, upUser.getUserMobile());
            pst.setString(7, upUser.getUserImage());
            pst.setString(8, upUser.getUserIntro());

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

    public static UserBean getUserByUid(String uid) {

        Connection conn = null;
        Statement pst = null;
        UserBean bean = new UserBean();
        String query = "SELECT * FROM USER_REGISTRATION where  user_id = '" + uid + "'";
        //  String sql="SELECT * FROM USER_REGISTRATION WHERE USER_EMAIL='uname'";
        try {

            conn = DBConnect.getConnection();
            pst = conn.createStatement();

            ResultSet rs = pst.executeQuery(query);
            if (rs.next()) {
                bean.setUserFname(rs.getString("user_fname"));
                bean.setUserLname(rs.getString("user_lname"));
                bean.setUserEmail(rs.getString("user_email"));
                bean.setUserPassword(rs.getString("user_pass"));
                bean.setUserCountry(rs.getString("user_country"));
                bean.setUserGender(rs.getString("user_gender"));
                bean.setUserImage(rs.getString("user_image"));
                bean.setUserId(rs.getString("user_id"));
                bean.setLlogin(rs.getString("last_login"));
                bean.setLoginStatus(rs.getString("login_status"));
                bean.setUserIntro(rs.getString("user_intro"));
                bean.setUserBirthday(rs.getString("user_birthday"));

            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return bean;

    }

    public static void offlineLogin(String email) {
        Connection conn = null;
        PreparedStatement pst = null;
        String query = "UPDATE USER_REGISTRATION set login_status='offline' where user_email = '" + email + "'";
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

    public static ArrayList<UserBean> getRecentBirthday() {
        ArrayList<UserBean> list = new ArrayList<UserBean>();
        Connection conn = null;
        Statement pst = null;
        UserBean bean = new UserBean();
        String query = "SELECT * FROM USER_REGISTRATION order by user_birthday desc limit 0,2";
        //  String sql="SELECT * FROM USER_REGISTRATION WHERE USER_EMAIL='uname'";
        try {

            conn = DBConnect.getConnection();
            pst = conn.createStatement();

            ResultSet rs = pst.executeQuery(query);
            while (rs.next()) {
                bean.setUserFname(rs.getString("user_fname"));
                bean.setUserLname(rs.getString("user_lname"));
                bean.setUserEmail(rs.getString("user_email"));
                bean.setUserPassword(rs.getString("user_pass"));
                bean.setUserCountry(rs.getString("user_country"));
                bean.setUserGender(rs.getString("user_gender"));
                bean.setUserImage(rs.getString("user_image"));
                bean.setUserId(rs.getString("user_id"));
                bean.setLlogin(rs.getString("last_login"));
                list.add(bean);
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;

    }

}
