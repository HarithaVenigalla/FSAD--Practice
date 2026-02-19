package com.klu.services;

import org.springframework.stereotype.Service;

@Service
public class CalcService {
	public int add(int a, int b) {
		return a+b;
	}
	public int subtract(int a, int b) {
		return a-b;
	}
	public int multiplt(int a,int b) {
		return a*b;
	}
	public double divide(int a, int b) {
		if(b==0) {
			throw new ArithmeticException("Division by zero is not allowed");
		}
		return (double) a/b;
		
	}
	public int modulo(int a, int b) {
		return a%b;
	}

}