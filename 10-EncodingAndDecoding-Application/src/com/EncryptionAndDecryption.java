package com;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class EncryptionAndDecryption {

	public static void main(String[] args) {
		String name="vaibhav";
		try {
			//It it unidirectional encryption format.
			MessageDigest mgDigest = MessageDigest.getInstance("SHA-256");
			mgDigest.reset();
			mgDigest.update(name.getBytes());
			byte[] encryptedBytes = mgDigest.digest();
			System.out.println("Actual Value : "+name);
			System.out.println("Encrypted Text : "+new String(encryptedBytes));
			//Encrypted value contains special characters which is not allowed to transfer over network therefore we need to convert this data into encoded format.
			byte[] encodeEncryptValue = Base64.getEncoder().encode(encryptedBytes);
			System.out.println("Encoded Encrypted value : "+new String(encodeEncryptValue));
			
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
	}
	
}
