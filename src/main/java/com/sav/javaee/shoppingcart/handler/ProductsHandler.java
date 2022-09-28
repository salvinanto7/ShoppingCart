package com.sav.javaee.shoppingcart.handler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sav.javaee.shoppingcart.dto.DBConfDTO;
import com.sav.javaee.shoppingcart.entity.ProductsEntity;
import com.sav.javaee.shoppingcart.services.ProductService;

public class ProductsHandler {
	public List<ProductsEntity> handleRequest(HttpServletRequest request,HttpServletResponse response ){
		DBConfDTO dbConf = (DBConfDTO) request.getServletContext().getAttribute("dbConf");
		ProductService prodService = new ProductService(dbConf);
		List<ProductsEntity> prodList = prodService.getAllProducts();
		return prodList;
	}
}
