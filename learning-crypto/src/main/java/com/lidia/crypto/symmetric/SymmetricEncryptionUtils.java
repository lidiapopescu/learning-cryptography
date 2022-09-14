package com.lidia.crypto.symmetric;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

public class SymmetricEncryptionUtils {
	
	private static final String AES = "AES";
	private static final String AES_CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";
	
	public static SecretKey createAESKey() throws Exception {
		
		SecureRandom secureRandom = new SecureRandom();
		KeyGenerator keyGenerator = KeyGenerator.getInstance(AES);
		keyGenerator.init(256, secureRandom);
		return keyGenerator.generateKey();
	}
	
	public static byte[] createInitializationVector() {
		byte[] initializationVector = new byte[16];
		SecureRandom secureRandom = new SecureRandom();
		//get a random set of bytes
		//will populate the array with true, cryptographic random bytes
		secureRandom.nextBytes(initializationVector);
		return initializationVector;
	}
	
	/**
	 * Basic AES encryption using a modern block cipher and a 256-bit key
	 * */
	public static byte[] performAESEncryption(String plaintText, SecretKey secretKey, byte[] initializationVector) throws Exception {
		Cipher cipher = Cipher.getInstance(AES_CIPHER_ALGORITHM);
		IvParameterSpec ivParameterSpec = new IvParameterSpec(initializationVector);
		cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec);
		return cipher.doFinal(plaintText.getBytes());
	}
	
	/**
	 * Basic AES decryption using a modern block cipher and a 256-bit key
	 * */
	public static String performAESDecryption(byte[] cipherText , SecretKey secretKey, byte[] initializationVector) throws Exception {
		Cipher cipher = Cipher.getInstance(AES_CIPHER_ALGORITHM);
		IvParameterSpec ivParameterSpec = new IvParameterSpec(initializationVector);
		cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);
		byte[] result = cipher.doFinal(cipherText);
		return new String(result);
	}
}
