package com.jas.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jas.service.SumService;

public class SumOfTwoController {

	SumService sumService;

	@RequestMapping(value = "/add")
	public int add(@RequestParam("a") int a, @RequestParam("b") int b) {
		return sumService.add(a, b);
	}
}
