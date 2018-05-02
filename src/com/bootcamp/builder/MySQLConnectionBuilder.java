package com.bootcamp.builder;

public class MySQLConnectionBuilder implements SQLConnectionBuilder {
	/**
	 * The concrete connection
	 */
	private SQLConnection connection;

	/**
	 * Constructor of the concrete builder
	 * Here the connection is made
	 */
	public MySQLConnectionBuilder() {
		this.connection = new SQLConnection();
	}

	/**
	 * The name is setted to the connection
	 */
	@Override public void buildName() {
		this.connection.setName("MySQL");
	}

	/**
	 * @return the connection object
	 */
	@Override public SQLConnection getSQLConnection() {
		return connection;
	}
}