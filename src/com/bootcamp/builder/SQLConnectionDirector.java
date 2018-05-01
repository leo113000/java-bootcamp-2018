package com.bootcamp.builder;

public class SQLConnectionDirector {

	private SQLConnectionBuilder builder;

	public SQLConnectionDirector(SQLConnectionBuilder builder) {
		this.builder = builder;
	}

	public void constructSQLConnection(){
		this.builder.buildName();
	}

	public SQLConnection getConnection(){
		return this.builder.getSQLConnection();
	}

}
