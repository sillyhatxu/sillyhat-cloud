package com.sillyhat.groovy.chapter4.params.dto;

import lombok.Data;

import java.util.Date;

@Data
public class Adder {
	
	public int add(int a, int b)
	{
		return a+b;
	}

}
