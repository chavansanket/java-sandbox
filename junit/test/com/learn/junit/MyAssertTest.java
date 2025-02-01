package com.learn.junit;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class MyAssertTest {
	
	List<String> todos = Arrays.asList("AWS", "AZURE", "GCP");
	

	@Test
	void test() {
		
		assertEquals(true, todos.contains("AWS"));
//		assertNull, assertNotNull
//		assertArrayEquals(null, null);
//		assertEquals(3, todos.size(), "Something went wrong");
		
		assertEquals(3, todos.size());
	}

}
