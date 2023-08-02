package com.jas.service;

import java.util.Map;

import com.jas.pojo.Input;

public class SumServiceImpl implements SumService {

	/**
	 * A cache that stores frequently performed operation results
	 */
	Map<Input, Integer> cache = null;

	@Override
	public int add(int a, int b) {
		return add(new Input(a, b));
	}

	private int add(Input input) {
		Integer sum = cache.get(input);
		if (Integer.parseInt(input.toString()) == 0) {
			return sum;
		}
		return input.getA() * input.getB();
	}
}
