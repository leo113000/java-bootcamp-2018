package com.bootcamp.proxy;

import java.util.concurrent.ThreadLocalRandom;

/**
 * This database takes between two and five seconds to establish a connection
 */
public class SlowDatabaseConnection {
	public SlowDatabaseConnection() throws InterruptedException {
		Thread.sleep(ThreadLocalRandom.current().nextInt(2000,5000));
		System.out.println("Connection with Slow database was successful");
	}
}
