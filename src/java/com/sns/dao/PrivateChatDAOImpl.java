
package com.sns.dao;

import com.sns.model.ChatBean;
import com.sns.model.PrivateChatBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class PrivateChatDAOImpl implements PrivateChatDAO {

    @Override
    public int insertChat(PrivateChatBean chatBean) {
        
         Connection conn = null;
        PreparedStatement pst = null;
        int status = 0;
        
        try {
            conn = DBConnect.getConnection();
            
            pst = conn.prepareStatement("insert into privatechat(sender_id,reciver_id,user_name,message) values(?,?,?,?)");
            
            pst.setString(1, chatBean.getUserId());
            pst.setString(3, chatBean.getName());
            pst.setString(4, chatBean.getMessage());
            pst.setString(2, chatBean.getrId());
           System.out.println(chatBean.getrId());
            
           
            
            int rowCount;
            rowCount = pst.executeUpdate();
            if (rowCount > 0) {
               status=1;
            } else {
                status = 0;
            }
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
       return status;
        
        
        
    }

    @Override
    public ArrayList<PrivateChatBean> selectChat(String sid, String rid) {
            
    ArrayList<PrivateChatBean> list = new ArrayList<PrivateChatBean>();
		Connection conn=null;
		 Statement pst=null;
		ResultSet rs=null;
		//String query = "SELECT * FROM USER_REGISTRATION where user_email = '" + uname + "'";
		String query = "SELECT * FROM privatechat where sender_id = '" + sid + "' and  reciver_id='" + rid + "' or sender_id='" + rid + "' and reciver_id='" + sid + "' ";
	try{
	 conn =DBConnect.getConnection();
		 
	
		 pst = conn.createStatement();
		
		 rs = pst.executeQuery(query);
		
		while(rs.next()){
			
			//create object
			PrivateChatBean ob =  new PrivateChatBean();
			
			//retrieve data from record and store in the object
			//ob.setId(rs.getString(1));
			ob.setName(rs.getString("user_name"));
			ob.setMessage(rs.getString("message"));
                        ob.setDate(rs.getString("date"));
			list.add(ob);
			
		}
		
	}
	catch(Exception ex){
		ex.printStackTrace();
	}
	finally{
	DBConnect.closeOracleConnection(conn);
	//DBConnect.closePreparedStatementConnection(pst);
	DBConnect.closeResultSetConnection(rs);
	
}
	 return list;
    }}
