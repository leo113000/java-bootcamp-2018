package com.bootcamp.builder;

public class MySQLConnectionBuilder implements SQLConnectionBuilder {

	private SQLConnection connection;

	public MySQLConnectionBuilder() {
		this.connection = new SQLConnection();
	}

	@Override public void buildName() {
		this.connection.setName("MySQL");
	}

	@Override public SQLConnection getSQLConnection() {
		return connection;
	}
}