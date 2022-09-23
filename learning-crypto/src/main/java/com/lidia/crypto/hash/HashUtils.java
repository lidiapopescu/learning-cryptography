package com.lidia.crypto.hash;

import java.io.ByteArrayOutputStream;
import java.security.MessageDigest;
import java.security.SecureRandom;

import org.mindrot.jbcrypt.BCrypt;

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
	
	public static String hashPassword(String password) {
//		BCrypt has already a password salt generator and in addition BCrypt stores the salt with the output string
//		e.g. 
//		The salt is:          $2a$10$iLwzRcIVC8lO19NLYmuE9O
//		The encrypted string: $2a$10$iLwzRcIVC8lO19NLYmuE9OHqqFUz2dPZmY6O8sx1U1Yk73zsqV1vq
//		The salt is actually embedded into the generated hash as well as the number of rounds that this actually took place.
		return BCrypt.hashpw(password, BCrypt.gensalt());
	}
	
	public static boolean verifyPassword(String password, String hashedPassword) {
		return BCrypt.checkpw(password, hashedPassword);
	}
 }
