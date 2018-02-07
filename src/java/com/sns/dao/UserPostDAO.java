
package com.sns.dao;

import com.sns.model.UserPostBean;
import java.util.ArrayList;

public interface UserPostDAO {
    
    public int insertPost(UserPostBean ob);
    public ArrayList<UserPostBean> getAllPost();
    public ArrayList<UserPostBean> getAllPost(String id);
}
