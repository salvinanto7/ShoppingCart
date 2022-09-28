package com.sav.javaee.shoppingcart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.sav.javaee.shoppingcart.entity.userRegistrationEntity;

public class UserRegistrationDAO {

	public int saveuserRegistration(userRegistrationEntity user, Connection mssqlconn) {
		int status =-1;
		String query ="insert into shopping_user_2824(email,name,password,phone) values(?,?,?,?)";
		try {
			PreparedStatement pst = mssqlconn.prepareStatement(query);
			pst.setString(1, user.getEmail());
			pst.setString(2, user.getName());
			pst.setString(3, user.getPassword());
			pst.setString(4, user.getPhone());
			
			status = pst.executeUpdate();
			pst.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

}
