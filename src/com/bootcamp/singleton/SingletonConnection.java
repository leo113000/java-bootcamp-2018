package com.bootcamp.singleton;

public class SingletonConnection {
	/**
	 *  Static attribute of the same type as the class that will contain the instance of himself
 	 */
	private static SingletonConnection instance;

	/**
	 * return the existence instance or a new one if not exists
	 * @return an instance
	 */
	public static SingletonConnection getInstance(){
		if(SingletonConnection.instance == null){
			SingletonConnection.instance = new SingletonConnection();
		}
		return SingletonConnection.instance;
	}

	/**
	 * The constructor is private to forbid the normal instantiation of the class
	 */
	private SingletonConnection() {
		System.out.println("Singleton Connection with database was successful");
	}
}
