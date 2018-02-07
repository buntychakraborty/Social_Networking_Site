
package com.sns.dao;

import com.sns.model.ChatBean;
import java.util.ArrayList;

public interface ChatDAO {
   public int insertChat(ChatBean bean);
   public ArrayList<ChatBean> selectChat();
}
