package com;

import java.util.Base64;

public class EncodingAndDecoding {

	public static void main(String[] args) {
		
		String name="Vaibhav";
		System.out.println("Actual value : "+name);
		String encode = Base64.getEncoder().encodeToString(name.getBytes());
		System.out.println("Encoded value : "+encode);
		System.out.println("Decoded value : "+new String(Base64.getDecoder().decode(encode)));
		
	}
	
}
