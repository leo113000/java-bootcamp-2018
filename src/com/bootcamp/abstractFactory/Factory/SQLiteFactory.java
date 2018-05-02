package com.bootcamp.abstractFactory.Factory;

import com.bootcamp.abstractFactory.Connection.AbstractSQLConnection;
import com.bootcamp.abstractFactory.Connection.SQLiteConnection;

public class SQLiteFactory implements ConnectionFactory {
	@Override public AbstractSQLConnection getConnection() {
		return new SQLiteConnection();
	}
}
