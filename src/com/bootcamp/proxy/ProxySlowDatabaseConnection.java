package com.bootcamp.proxy;

public class ProxySlowDatabaseConnection {

	private SlowDatabaseConnection slowConnection;

	public ProxySlowDatabaseConnection() {
		System.out.println("Proxy created");
	}

	public SlowDatabaseConnection getConnection() {
		if(this.slowConnection == null) {
			try {
				this.slowConnection = new SlowDatabaseConnection();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return this.slowConnection;
	}
}
