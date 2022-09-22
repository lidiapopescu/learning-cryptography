package com.lidia.crypto.hash;

import java.io.ByteArrayOutputStream;
import java.security.MessageDigest;
import java.security.SecureRandom;

public class HashUtils {

	private static final String SHA2_ALGORITHM = "SHA-256";
	
	public static byte[] generateRandomSalt() {
		byte[] salt =  new byte[16];
		SecureRandom secureRandom = new SecureRandom();
		secureRandom.nextBytes(salt);
		return salt;		
	}
	
	public static byte[] createSHA2Hash(String input, byte[] salt) throws Exception {
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		// copy two sets of bytes into one which is how the value gets added to an original input, in order to do the hashing
		byteStream.write(salt);
		byteStream.write(input.getBytes());
		byte[] valueToHash =  byteStream.toByteArray();
		
		MessageDigest messageDigest = MessageDigest.getInstance(SHA2_ALGORITHM);
		return messageDigest.digest(valueToHash);
	}
 }
