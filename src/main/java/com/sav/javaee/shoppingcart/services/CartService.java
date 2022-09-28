package com.sav.javaee.shoppingcart.services;

import java.awt.dnd.DropTarget;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.sav.javaee.shoppingcart.dao.AddCartDAO;
import com.sav.javaee.shoppingcart.dao.DBConnection;
import com.sav.javaee.shoppingcart.dao.GetCartDAO;
import com.sav.javaee.shoppingcart.dao.MSSQLConnection;
import com.sav.javaee.shoppingcart.dto.DBConfDTO;
import com.sav.javaee.shoppingcart.entity.ProductsEntity;

public class CartService {
	private DBConfDTO dbConf;

	public CartService(DBConfDTO dbConf) {
		this.dbConf = dbConf;
	}

	public List<ProductsEntity> getCartProducts(String usermail) {
		DBConnection conn = new MSSQLConnection();
		Connection mssqlconn = null;
		List<ProductsEntity> cartList = new ArrayList<>();
		try {
			mssqlconn = conn.createConnection(dbConf);
			GetCartDAO getCart = new GetCartDAO();
			cartList = getCart.getAllProducts(mssqlconn, usermail);
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
		return cartList;
	}

	public String addToCart(String email, int prodId) {
		DBConnection conn = new MSSQLConnection();
		Connection mssqlconn = null;
		String message = null;
		int status = -1;
		try {
			mssqlconn = conn.createConnection(dbConf);
			AddCartDAO addCart = new AddCartDAO();
			status = addCart.saveCartItems(prodId, email, mssqlconn);
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
			message = status > 0 ? "success" : "failure";
		}
		return message;
	}
}
