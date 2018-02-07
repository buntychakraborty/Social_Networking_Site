
package com.sns.dao;

import com.sns.model.PrivateChatBean;
import java.util.ArrayList;

public interface PrivateChatDAO {
    public int insertChat(PrivateChatBean bean);
   public ArrayList<PrivateChatBean> selectChat(String sid,String rid);
}
