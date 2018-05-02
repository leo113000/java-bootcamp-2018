package com.bootcamp.abstractFactory.Factory;

import com.bootcamp.abstractFactory.Connection.AbstractSQLConnection;
import com.bootcamp.abstractFactory.Connection.SQLServerConnection;

public class SQLServerFactory implements ConnectionFactory {

	@Override public AbstractSQLConnection getConnection() {
		return new SQLServerConnection();
	}
}
