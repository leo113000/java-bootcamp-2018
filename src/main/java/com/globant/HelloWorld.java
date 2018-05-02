package com.globant;

public class HelloWorld {
	/**
	 * @param name name to say hello
	 * @return greeting
	 */
	public String getGreetingMessage(String name) {
		String greeting;
		if (name == null || name == "") {
			greeting = "Hello World!";
		} else {
			greeting = "Hello " + name + "!";
		}

		return greeting;
	}

}
