package com.bootcamp.abstractFactory;

import com.bootcamp.abstractFactory.Factory.*;

public class FactoryProducer {
	/**
	 * @param type String to indicate the type of connection wanted
	 * @return the desired ConnectionFactory
	 */
	public static ConnectionFactory getFactory(String type) {
		ConnectionFactory factory = null;
		if (type.equalsIgnoreCase("MYSQL")) {
			factory = new MySQLFactory();
		} else if (type.equalsIgnoreCase("POSTGRESQL")) {
			factory = new PostgreSQLFactory();
		} else if (type.equalsIgnoreCase("SQLITE")) {
			factory = new SQLiteFactory();
		} else if (type.equalsIgnoreCase("SQLSERVER")) {
			factory = new SQLServerFactory();
		}
		return factory;
	}
}
