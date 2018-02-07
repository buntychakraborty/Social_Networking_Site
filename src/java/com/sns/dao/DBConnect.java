
package com.sns.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnect {

	public static Connection getConnection() {
		Connection cons = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			cons = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/socialnetwork", "root", "12345");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cons;
	}
        
        public static void closeOracleConnection(Connection conn){
		if(conn!=null){
			try{
				conn.close();
			}
			catch(SQLException e){			}
			}
		}
	public static void closePreparedStatementConnection(PreparedStatement pst){
		if(pst!=null){
			try{
				pst.close();
			}
			catch(SQLException e){}
			}
		}
	public static void closeResultSetConnection(ResultSet rs){
		if(rs!=null){
			try{
			rs.close();
			}
			catch(SQLException e){}
			}
		}
}