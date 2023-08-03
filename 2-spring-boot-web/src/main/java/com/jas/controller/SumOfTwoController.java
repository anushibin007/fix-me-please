package com.jas.controller;

import com.generic.service.SumService;

public class SumOfTwoController {

	SumService sumService;

	/**
	 * Expected request: http://localhost:8080/sum?a=10&b=20
	 * 
	 * @param a operand 1
	 * @param b operand 2
	 * @return sum of operands 1 and 2
	 */
	public int add(int a, int b) {
		return sumService.add(a, b);
	}

}
