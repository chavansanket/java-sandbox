package com.learn.junit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MyBeforeAfterTest {
	
	@AfterEach
	void afterEach() {
		System.out.println("after each");
	}
	
	
	@AfterAll
	static void afterAll() {
		System.out.println("after all");
	}
	
	
	@BeforeEach
	void beforeEach() {
		System.out.println("before each");
	}
	
	@BeforeAll
	static void beforeAllEach() {
		System.out.println("before all each");
	}

	@Test
	void test1() {
		System.out.println("Test 1");
	}
	
	@Test
	void test2() {
		System.out.println("Test 2");
	}
	
	@Test
	void test3() {
		System.out.println("Test 3");
	}
	

}


