package com.sav.javaee.shoppingcart.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.sav.javaee.shoppingcart.dto.DBConfDTO;

public class MSSQLConnection implements DBConnection{
	private Class driverClass;
	@Override
	public Connection createConnection(DBConfDTO dbConf) throws SQLException {
		loadDriver(dbConf.getDriver());
		return DriverManager.getConnection(dbConf.getUrl(),dbConf.getUser(),dbConf.getPassword());
	}

	private void loadDriver(String driver) {
		try {
			driverClass = Class.forName(driver);
			System.out.println("driver loaded");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}

}
