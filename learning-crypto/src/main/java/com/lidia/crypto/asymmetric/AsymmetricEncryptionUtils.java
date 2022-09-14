package com.lidia.crypto.asymmetric;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;

import javax.crypto.Cipher;

public class AsymmetricEncryptionUtils {

	private static final String RSA = "RSA";
	
	public static KeyPair generateRSAkeyPair() throws Exception {
		
		SecureRandom secureRandom = new SecureRandom();
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(RSA);
		keyPairGenerator.initialize(4096, secureRandom);
		return keyPairGenerator.generateKeyPair();
	}
	
	public static byte[] performRSAEncryption(String plaintText, PrivateKey privateKey) throws Exception {
		Cipher cipher = Cipher.getInstance(RSA);
		cipher.init(Cipher.ENCRYPT_MODE, privateKey);
		return cipher.doFinal(plaintText.getBytes());
	}
	
	public static String performRSADecryption(byte[] cipherText, PublicKey publicKey) throws Exception {
		Cipher cipher = Cipher.getInstance(RSA);
		cipher.init(Cipher.DECRYPT_MODE, publicKey);
		byte[] result = cipher.doFinal(cipherText);
		return new String(result);
	}
}
