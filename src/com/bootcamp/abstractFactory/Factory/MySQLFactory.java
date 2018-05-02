package com.bootcamp.abstractFactory.Factory;

import com.bootcamp.abstractFactory.Connection.AbstractSQLConnection;
import com.bootcamp.abstractFactory.Connection.MySQLConnection;

public class MySQLFactory implements ConnectionFactory {
	@Override public AbstractSQLConnection getConnection() {
		return new MySQLConnection();
	}
}
