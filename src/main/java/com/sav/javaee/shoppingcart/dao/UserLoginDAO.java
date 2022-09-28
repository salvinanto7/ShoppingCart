package com.sav.javaee.shoppingcart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sav.javaee.shoppingcart.entity.UserLoginEntity;

public class UserLoginDAO {
	
	public int checkUserLogin(UserLoginEntity user, Connection conn) throws SQLException{
		String query = "select * from shopping_user_2824 where email=? and password = ?";
		
		int status = -1;
		try {
			PreparedStatement pst = conn.prepareStatement(query);
			pst.setString(1,user.getEmail());
			pst.setString(2,user.getPassword());
			ResultSet rs = pst.executeQuery();
			
			if(rs.next()) {
				status=1;
			}
			rs.close();
			pst.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}
	
}
