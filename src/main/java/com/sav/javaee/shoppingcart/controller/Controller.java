package com.sav.javaee.shoppingcart.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sav.javaee.shoppingcart.dto.DBConfDTO;
import com.sav.javaee.shoppingcart.entity.ProductsEntity;
import com.sav.javaee.shoppingcart.handler.CartHandler;
import com.sav.javaee.shoppingcart.handler.Handler;
import com.sav.javaee.shoppingcart.handler.LoginHandler;
import com.sav.javaee.shoppingcart.handler.ProductsHandler;
import com.sav.javaee.shoppingcart.handler.RegistrationHandler;

/**
 * Servlet implementation class Controller
 */
//@WebServlet("/controller")
public class Controller extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		HttpSession session = request.getSession(false);
		System.out.println(session);
		String userAction = request.getParameter(UserAction.USER_ACTION);

		if (userAction.equals(UserAction.SUBMIT_REGISTRATION)) {
			Handler handle = new RegistrationHandler();
			String viewId = handle.handleRequest(request, response);
			session = addToSession(request);
			request.getRequestDispatcher(viewId).forward(request, response);
		} else if (userAction.equals(UserAction.REGISTER)) {
			response.sendRedirect("register.jsp");
		} else if (userAction.equals(UserAction.SUBMIT_LOGIN)) {
			Handler handle = new LoginHandler();
			String viewId = handle.handleRequest(request, response);
			session = addToSession(request);
			request.getRequestDispatcher(viewId).forward(request, response);
		} else if (userAction.equals(UserAction.LOGIN)) {
			response.sendRedirect("login.jsp");
		} else if (strip(userAction).equals(UserAction.ADDTOCART)) {
			if (session.getAttribute("user") != null) {
				int prodId = Integer
						.parseInt(userAction.substring(userAction.lastIndexOf("/") + 1, userAction.length()));

				CartHandler handle = new CartHandler();
				String email = (String) session.getAttribute("email");
				String message = handle.addToCart(prodId, email, request, response);
				System.out.println(message);
				if (message.equals("success")) {
					System.out.println("no errors");
					request.getRequestDispatcher("products.jsp");
				}
			} else {
				response.sendRedirect("login.jsp");
			}
		} else if (userAction.equals(UserAction.PRODUCTS)) {
			ProductsHandler handle = new ProductsHandler();
			List<ProductsEntity> prodList = new ArrayList<>();
			prodList = handle.handleRequest(request, response);
			request.setAttribute("products", prodList);
			request.getRequestDispatcher("products.jsp").forward(request, response);
		} else if (userAction.equals(UserAction.CART)) {
			CartHandler handle = new CartHandler();
			List<ProductsEntity> cartList = new ArrayList<>();
			cartList = handle.handleRequest(request, response);
			request.setAttribute("cart", cartList);
			request.getRequestDispatcher("cart.jsp").forward(request, response);
		}else if(userAction.equals(UserAction.LOGOUT)) {
			session.invalidate();
			response.sendRedirect("login.jsp");
		}
	}

	private String strip(String userAction) {
		String action = userAction.substring(0, userAction.lastIndexOf("/"));
		System.out.println(action);
		return action;
	}

/////////////////////////////////////////////////////////////////////////////////////
	private HttpSession addToSession(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session.getAttribute("email") == null) {
			session.setAttribute("email", request.getParameter("email"));
			LoginHandler handle = new LoginHandler();
			String name = handle.getUserData(request);
			session.setAttribute("user", name);
		}
		return session;
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		DBConfDTO dbConf = new DBConfDTO();

		dbConf.setDriver(config.getInitParameter("driver"));
		dbConf.setUrl(config.getInitParameter("url"));
		dbConf.setUser(config.getInitParameter("user"));
		dbConf.setPassword(config.getInitParameter("password"));

		config.getServletContext().setAttribute("dbConf", dbConf);
	}

}
