package com.bootcamp.abstractFactory.Factory;

import com.bootcamp.abstractFactory.Connection.AbstractSQLConnection;

/**
 * The "abstract factory" itself
 */
public interface ConnectionFactory {
	AbstractSQLConnection getConnection();
}
