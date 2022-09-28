package com.sav.javaee.shoppingcart.services;

import java.sql.Connection;
import java.sql.SQLException;

import com.sav.javaee.shoppingcart.dao.DBConnection;
import com.sav.javaee.shoppingcart.dao.MSSQLConnection;
import com.sav.javaee.shoppingcart.dao.UserRegistrationDAO;
import com.sav.javaee.shoppingcart.dto.DBConfDTO;
import com.sav.javaee.shoppingcart.entity.userRegistrationEntity;

public class UserRegistrationService {
	
	private DBConfDTO dbConf;
	public UserRegistrationService(DBConfDTO dbConf) {
		this.dbConf = dbConf;
	}

	public String saveUserRegistration(userRegistrationEntity user) {
		DBConnection conn = new MSSQLConnection();
		Connection mssqlconn = null;
		String message = null;
		int status = -1;
		try {
			mssqlconn = conn.createConnection(dbConf);
			UserRegistrationDAO userRegister = new UserRegistrationDAO(); 
			status = userRegister.saveuserRegistration(user,mssqlconn);
		}catch(SQLException e) {
			e.printStackTrace();
		}finally{
			try {
			if(mssqlconn!=null && !mssqlconn.isClosed()) {
				
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

}
