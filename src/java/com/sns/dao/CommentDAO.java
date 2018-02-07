
package com.sns.dao;

import com.sns.model.CommentBean;
import java.util.ArrayList;

public interface CommentDAO {
    public int insertComment(CommentBean bean);
     public ArrayList<CommentBean> getAllComment(String id);
    
}
