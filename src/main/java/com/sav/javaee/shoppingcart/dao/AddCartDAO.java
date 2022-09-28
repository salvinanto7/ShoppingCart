package com.sav.javaee.shoppingcart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AddCartDAO {

	public int saveCartItems(int prodId, String email, Connection mssqlconn) {
		int status =-1;
		String insertQuery = "insert into shopping_cart_2824(userid,productid) values(?,?)";
		String useridQuery = "select id from shopping_user_2824 where email=?";
		
		int userId = 0;
		
		try {
			PreparedStatement pst = mssqlconn.prepareStatement(useridQuery);
			pst.setString(1,email);
			ResultSet rs = pst.executeQuery();
			
			if(rs.next()) {
				userId = rs.getInt("id");
				System.out.println(userId);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		try {
			PreparedStatement pst = mssqlconn.prepareStatement(insertQuery);
			pst.setInt(1,userId);
			pst.setInt(2,prodId);
			status = pst.executeUpdate();
			pst.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return status;
	}

}
