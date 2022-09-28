package com.sav.javaee.shoppingcart.handler;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sav.javaee.shoppingcart.dto.DBConfDTO;
import com.sav.javaee.shoppingcart.entity.ProductsEntity;
import com.sav.javaee.shoppingcart.services.CartService;

public class CartHandler {
	public List<ProductsEntity> handleRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		DBConfDTO dbConf = (DBConfDTO) request.getServletContext().getAttribute("dbConf");
		CartService cartService = new CartService(dbConf);
		HttpSession session = request.getSession(false);
		String userEmail = (String) session.getAttribute("email");
		List<ProductsEntity> cartList = cartService.getCartProducts(userEmail);
		return cartList;
	}
	
	public String addToCart(int prodId, String email,HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		DBConfDTO dbConf = (DBConfDTO) request.getServletContext().getAttribute("dbConf");
		CartService cartService = new CartService(dbConf);
		HttpSession session = request.getSession(false);
		String message = cartService.addToCart(email,prodId);
		return message;
	}
}
