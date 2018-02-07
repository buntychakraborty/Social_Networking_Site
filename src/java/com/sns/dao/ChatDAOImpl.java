/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sns.dao;

import com.sns.model.ChatBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Bunty
 */
public class ChatDAOImpl implements ChatDAO {

    @Override
    public int insertChat(ChatBean chatBean) {
        Connection conn = null;
        PreparedStatement pst = null;
        int status = 0;
        
        try {
            conn = DBConnect.getConnection();
            
            pst = conn.prepareStatement("insert into chat(name,message) values(?,?)");
            
            pst.setString(1, chatBean.getName());
            pst.setString(2, chatBean.getMessage());
           System.out.println(chatBean.getName());
            
           
            
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
    public ArrayList<ChatBean> selectChat() {
        ArrayList<ChatBean> list = new ArrayList<ChatBean>();
		Connection conn=null;
		 Statement pst=null;
		ResultSet rs=null;
		
		
	try{
	 conn =DBConnect.getConnection();
		 
	
		 pst = conn.createStatement();
		
		 rs = pst.executeQuery("select * from chat order by 1 desc limit 12");
		
		while(rs.next()){
			
			//create object
			ChatBean ob =  new ChatBean();
			
			//retrieve data from record and store in the object
			ob.setId(rs.getString(1));
			ob.setName(rs.getString(2));
			ob.setMessage(rs.getString(3));
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
        
    }
    
}
