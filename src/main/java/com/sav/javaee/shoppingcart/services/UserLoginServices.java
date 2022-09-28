package com.sav.javaee.shoppingcart.services;

import java.sql.Connection;
import java.sql.SQLException;

import com.sav.javaee.shoppingcart.dao.DBConnection;
import com.sav.javaee.shoppingcart.dao.MSSQLConnection;
import com.sav.javaee.shoppingcart.dao.UserRegistrationDAO;
import com.sav.javaee.shoppingcart.dto.DBConfDTO;
import com.sav.javaee.shoppingcart.entity.UserLoginEntity;
import com.sav.javaee.shoppingcart.entity.userRegistrationEntity;

public class UserLoginServices {
	
	
	public String checkLogin(UserLoginEntity user,DBConfDTO dbConf) {
		DBConnection conn = new MSSQLConnection();
		Connection mssqlconn = null;
		String message = null;
		int status = -1;
		try {
			mssqlconn = conn.createConnection(dbConf);
			UserLoginDAO userLogin = new UserLoginDAO(); 
			status = userLogin.checkUserLogin(user,mssqlconn);
		}catch(SQLException e) {
			e.printStackTrace();
		}finally{
			try {
			if(conn!=null && !mssqlconn.isClosed()) {
				
				mssqlconn.close();
				}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			message = status>0?"success":"failure";
		}
		return message;
	}
	
	public String getuserData(String parameter, DBConfDTO dbConf) {
		DBConnection conn = new MSSQLConnection();
		Connection mssqlconn = null;
		String name = null;
		
		try {
			mssqlconn = conn.createConnection(dbConf);
			UserDataDAO userData = new UserDataDAO(); 
			name = userData.getUser(parameter,mssqlconn);
		}catch(SQLException e) {
			e.printStackTrace();
		}finally{
			try {
			if(conn!=null && !mssqlconn.isClosed()) {
				
				mssqlconn.close();
				}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}			
		}
		return name;
	}
}
