package com.bootcamp.abstractFactory.Factory;

import com.bootcamp.abstractFactory.Connection.AbstractSQLConnection;
import com.bootcamp.abstractFactory.Connection.PostgreSQLConnection;

public class PostgreSQLFactory implements ConnectionFactory {
	@Override public AbstractSQLConnection getConnection() {
		return new PostgreSQLConnection();
	}
}
