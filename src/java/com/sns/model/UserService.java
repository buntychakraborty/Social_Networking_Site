
package com.sns.model;

import com.sns.dao.UserDAO;
import com.sns.dao.UserDAOImpl;

public class UserService {
    public String register(UserBean user) {

        UserDAO dao = new UserDAOImpl();
        int status = dao.insertUser(user);
        if (status > 0) {
            return ("Record saved successfully!");

        } else {
            return ("Sorry! unable to save record'");

        }

    }
}
