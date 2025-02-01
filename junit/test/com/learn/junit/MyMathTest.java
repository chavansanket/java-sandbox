package com.learn.junit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MyMathTest {

	private MyMath math = new MyMath();
	
	//Absence of failure is success.
	//Test Condition or Assert
	//assertEquals(expected,actual);
	//@BeforeAll and @AfterAll are class level method, so make them static in particular class where you are using
	
	@Test
	void calculateSum_ThreeMemberArray() {
	
		assertEquals(4, math.calculateSum(new int[] {1, 2, 3}));
	}

//	{}=> 0
	@Test
	void calculateSum_ZeroLengthArray() {
		
		assertEquals(0, math.calculateSum(new int[] {}));
	}
}
