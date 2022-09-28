package com.sav.javaee.shoppingcart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.sav.javaee.shoppingcart.entity.ProductsEntity;

public class GetProductsDAO {

	public List<ProductsEntity> getAllProducts(Connection mssqlconn) {
		String query = "select * from shopping_product_2824";
		List<ProductsEntity> prodList = new ArrayList<>();
		try {
			PreparedStatement pst = mssqlconn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				
				ProductsEntity prod = new ProductsEntity(rs.getInt("id"),rs.getString("name"),rs.getString("description"),rs.getInt("price"));
				prodList.add(prod);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prodList;
	}

}
