package com.globant;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestHelloWorld {

	private HelloWorld h;

	/**
	 * Instantiate the object with the method to be tested
	 */
	@Before public void setUp() {
		h = new HelloWorld();
	}

	/**
	 * Test when the method receives an empty string
	 */
	@Test public void testHelloEmpty() {
		assertEquals(h.getGreetingMessage(""), "Hello World!");
	}

	/**
	 * Test when the method receives null
	 */
	@Test public void testHelloNull() {
		assertEquals(h.getGreetingMessage(null), "Hello World!");
	}

	/**
	 * Test when the method receives a name
	 */
	@Test public void testHelloWorld() {
		assertEquals(h.getGreetingMessage("Leo"), "Hello Leo!");
	}
}
