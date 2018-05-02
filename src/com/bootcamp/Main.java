package com.bootcamp;

import com.bootcamp.abstractFactory.Connection.AbstractSQLConnection;
import com.bootcamp.abstractFactory.Factory.ConnectionFactory;
import com.bootcamp.abstractFactory.FactoryProducer;
import com.bootcamp.builder.MySQLConnectionBuilder;
import com.bootcamp.builder.SQLConnectionDirector;
import com.bootcamp.proxy.ProxySlowDatabaseConnection;
import com.bootcamp.proxy.SlowDatabaseConnection;
import com.bootcamp.singleton.SingletonConnection;

public class Main {
	/**
	 * Main to do all the "glue code" needed to show the patterns's work
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		// Singleton
		SingletonConnection singletonConnection = SingletonConnection.getInstance();
		// Proxy
		ProxySlowDatabaseConnection proxy = new ProxySlowDatabaseConnection();
		SlowDatabaseConnection proxyConnection = proxy.getConnection();
		// Builder
		SQLConnectionDirector director = new SQLConnectionDirector(new MySQLConnectionBuilder());
		director.constructSQLConnection();
		String connectionName = director.getConnection().getName();
		System.out.println("The database connected is: " + connectionName);
		// Abstract Factory
		ConnectionFactory factory = FactoryProducer.getFactory("SQLSERVER");
		if (factory != null) {
			AbstractSQLConnection connection = factory.getConnection();
			System.out.println("The database connected is: " + connection.getConnectionType());
		}
	}
}
