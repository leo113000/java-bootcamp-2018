package com.bootcamp.builder;

public class SQLConnectionDirector {
	/**
	 * The instance of the builder
	 */
	private SQLConnectionBuilder builder;

	/**
	 *
	 * @param builder concrete builder
	 */
	public SQLConnectionDirector(SQLConnectionBuilder builder) {
		this.builder = builder;
	}

	/**
	 * This method calls the methods from the builder
	 */
	public void constructSQLConnection(){
		this.builder.buildName();
	}

	/**
	 *
	 * @return the connection
	 */
	public SQLConnection getConnection(){
		return this.builder.getSQLConnection();
	}

}
