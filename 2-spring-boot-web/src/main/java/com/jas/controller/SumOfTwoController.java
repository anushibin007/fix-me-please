package com.jas.controller;

import com.jas.service.SumService;

public class SumOfTwoController {

	SumService sumService;

	public int add(int a, int b) {
		return sumService.add(a, b);
	}

}
