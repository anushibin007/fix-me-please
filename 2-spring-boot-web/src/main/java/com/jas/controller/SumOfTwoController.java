package com.jas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jas.service.SumService;

@Controller
public class SumOfTwoController {

	@Autowired
	SumService sumService;

	@GetMapping(value = "/add")
	public int add(@RequestParam("a") int a, @RequestParam("b") int b) {
		return sumService.add(a, b);
	}
}
