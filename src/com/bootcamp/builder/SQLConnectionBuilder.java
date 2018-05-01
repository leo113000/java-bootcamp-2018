package com.bootcamp.builder;

/**
 * This interface will be implemented by the concrete builders
 */
public interface SQLConnectionBuilder {
	void buildName();
	SQLConnection getSQLConnection();
}
