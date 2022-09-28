package com.sav.javaee.shoppingcart.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.sav.javaee.shoppingcart.dto.DBConfDTO;

public interface DBConnection {
	Connection createConnection(DBConfDTO dbConf) throws SQLException;
}
