package com.sav.javaee.shoppingcart.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sav.javaee.shoppingcart.dto.DBConfDTO;
import com.sav.javaee.shoppingcart.entity.userRegistrationEntity;
import com.sav.javaee.shoppingcart.services.UserRegistrationService;

public class RegistrationHandler implements Handler{


	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		userRegistrationEntity regEntity = createRegistration(request);
		DBConfDTO dbConf = (DBConfDTO) request.getServletContext().getAttribute("dbConf");
		UserRegistrationService rs = new UserRegistrationService(dbConf);
		String message = rs.saveUserRegistration(regEntity);
		
		if(message.equals("success")) {
			return "login.jsp";
		}
		return "register.jsp";
	}

	private userRegistrationEntity createRegistration(HttpServletRequest request) {
		userRegistrationEntity reg = new userRegistrationEntity();
		reg.setEmail(request.getParameter("email"));
		reg.setName(request.getParameter("name"));
		reg.setPassword(request.getParameter("password"));
		reg.setPhone(request.getParameter("phone"));
		
		return reg;
	}

}
