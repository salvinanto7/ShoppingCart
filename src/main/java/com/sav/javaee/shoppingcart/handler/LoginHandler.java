package com.sav.javaee.shoppingcart.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sav.javaee.shoppingcart.dto.DBConfDTO;
import com.sav.javaee.shoppingcart.entity.UserLoginEntity;

public class LoginHandler implements Handler{

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserLoginEntity user = createLoginData(request);
		DBConfDTO dbConf = (DBConfDTO) request.getServletContext().getAttribute("dbConf");
		UserLoginService rs = new UserLoginService();
		String message = rs.checkLogin(user,dbConf);
		
		if(message.equals("success")) {
			return "index.jsp";
		}else {
			return "login.jsp";
		}
	}

	private UserLoginEntity createLoginData(HttpServletRequest request) {
		UserLoginEntity user = new UserLoginEntity();
		user.setEmail(request.getParameter("email"));
		user.setPassword(request.getParameter("password"));
		return user;
	}

}
