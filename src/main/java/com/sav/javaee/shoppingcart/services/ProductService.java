package com.sav.javaee.shoppingcart.services;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.sav.javaee.shoppingcart.dao.DBConnection;
import com.sav.javaee.shoppingcart.dao.GetCartDAO;
import com.sav.javaee.shoppingcart.dao.GetProductsDAO;
import com.sav.javaee.shoppingcart.dao.MSSQLConnection;
import com.sav.javaee.shoppingcart.dto.DBConfDTO;
import com.sav.javaee.shoppingcart.entity.ProductsEntity;

public class ProductService {
	private DBConfDTO dbConf;

	public ProductService(DBConfDTO dbConf) {
		this.dbConf = dbConf;
	}

	public List<ProductsEntity> getAllProducts() {
		DBConnection conn = new MSSQLConnection();
		Connection mssqlconn = null;
		List<ProductsEntity> prodList = new ArrayList<>();
		try {
			mssqlconn = conn.createConnection(dbConf);
			GetProductsDAO getCart = new GetProductsDAO();
			prodList = getCart.getAllProducts(mssqlconn);
			mssqlconn.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null && !mssqlconn.isClosed()) {
					mssqlconn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return prodList;
	}
	
	
}
