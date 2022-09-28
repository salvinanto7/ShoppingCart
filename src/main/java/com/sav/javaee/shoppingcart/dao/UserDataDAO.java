package com.sav.javaee.shoppingcart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDataDAO {
	public String getUser(Connection conn,String email) throws SQLException{
		String query = "select * from shopping_user_2824 wher email=?";
		String name=null;
		
		try {
			PreparedStatement pst = conn.prepareStatement(query);
			pst.setString(1, email);
			ResultSet rs = pst.executeQuery();
			
			if(rs.next()) {
				name=rs.getString("name");
			}
			rs.close();
			pst.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return name;
	}
	
}
