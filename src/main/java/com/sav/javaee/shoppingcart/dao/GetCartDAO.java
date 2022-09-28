package com.sav.javaee.shoppingcart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.sav.javaee.shoppingcart.entity.ProductsEntity;

public class GetCartDAO {

	public List<ProductsEntity> getAllProducts(Connection mssqlconn, String usermail) {

		String query = "select p.id ,p.[name], p.[description], p.price from shopping_cart_2824 c join shopping_product_2824 p on c.productid = p.id where c.userid =?";
		String useridQuery = "select id from shopping_user_2824 where email=?";
		List<ProductsEntity> cartList = new ArrayList<>();
		int userId = 0;

		try {
			PreparedStatement pst = mssqlconn.prepareStatement(useridQuery);
			pst.setString(1, usermail);
			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				userId = rs.getInt("id");
				System.out.println(userId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			PreparedStatement pst= mssqlconn.prepareStatement(query);
			pst.setInt(1, userId);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				ProductsEntity prod = new ProductsEntity(rs.getInt("id"),rs.getString("name"),rs.getString("description"),rs.getInt("price"));
				cartList.add(prod);
			}
			pst.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return cartList;
	}
}
