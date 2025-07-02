package com.demo.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCrypt {
	
public String encoding(String pw) {
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		return encoder.encode(pw);
		
	}
	
	public boolean match(String pw1, String pw2) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		boolean result = encoder.matches(pw1, pw2);
		
		return result; 
	}

}
