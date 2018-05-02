package com.bootcamp.abstractFactory.Connection;

/**
 * The SQL connection abstract class
 */
public abstract class AbstractSQLConnection {
	private String connectionType;
	public AbstractSQLConnection(String connectionType) {
		this.connectionType = connectionType;
	}
	public String getConnectionType() {
		return connectionType;
	}
}
