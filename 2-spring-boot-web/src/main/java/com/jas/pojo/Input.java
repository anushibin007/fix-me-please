package com.jas.pojo;

/**
 * A POJO that holds the input state
 * 
 * @author anushibin007
 *
 */
public class Input {
	private int a;
	private int b;

	public Input(int a, int b) {
		this.a = a;
		this.b = b;
	}

	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}

	public int getB() {
		return b;
	}

	public void setB(int b) {
		this.b = b;
	}

	@Override
	public String toString() {
		return "Input [a=" + a + ", b=" + b + "]";
	}

}
