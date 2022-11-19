package com.example.demo.dto;

import java.security.SecureRandom;
import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class Utils {
	private final Random random = new SecureRandom();
	private final String alphabet = "0123456789AZERTYUIOPQSDFGHJKLMWXCVBNazertyuiopqsdfghjklmwxcvbn";
	
	public String generatedUserId(int length) {
		StringBuilder returnValue = new StringBuilder(length);
		for(int i=0;i<length;i++) {
			returnValue.append(alphabet.charAt(random.nextInt(alphabet.length())));
		}
		return new String(returnValue);
	}

}
