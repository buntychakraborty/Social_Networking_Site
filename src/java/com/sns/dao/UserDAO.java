package com.sns.dao;

import com.sns.model.UserBean;
import java.util.ArrayList;

public interface UserDAO {

    public int insertUser(UserBean newUser);  //
    public boolean userLogin(String uname, String pass);
    public ArrayList<UserBean> fetchAllRecord();
    public UserBean selectUser(String email);
    public int deleteUser(int id);
    public int UpdateUser(UserBean upUser,String email);
}
