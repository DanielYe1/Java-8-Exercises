package com.b2wdigital.offer;

import java.util.Optional;

public class OptionalTest {

	public static void main(String[] args) {
		Optional<String> optional = Optional.of("daniel");
		Optional<String> upper = optional.flatMap(OptionalTest::upper);
		
		System.out.println(optional);
		System.out.println(upper);
	}

	private static Optional<String> upper(String s){
		return Optional.of(s.toUpperCase());
	}
}
