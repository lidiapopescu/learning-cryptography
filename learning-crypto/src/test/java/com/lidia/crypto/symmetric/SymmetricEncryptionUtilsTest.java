package com.lidia.crypto.symmetric;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.crypto.SecretKey;
import javax.xml.bind.DatatypeConverter;

import org.junit.jupiter.api.Test;


class SymmetricEncryptionUtilsTest {
	
	@Test
	void createAESKey() throws Exception {
		SecretKey key = SymmetricEncryptionUtils.createAESKey();
		assertNotNull(key);
		System.out.println(DatatypeConverter.printHexBinary(key.getEncoded()));
	}
	
	@Test
	void testAESCryptoRoutine() throws Exception {
		SecretKey key =  SymmetricEncryptionUtils.createAESKey();
		byte[] initializationVector = SymmetricEncryptionUtils.createInitializationVector();
		String paintText = "This is the text we are going to hide in plaint sight";
		byte[] cipherText = SymmetricEncryptionUtils.performAESEncryption(paintText, key, initializationVector);
		assertNotNull(cipherText);
		System.out.println(DatatypeConverter.printHexBinary(cipherText));
		String decryptedText =  SymmetricEncryptionUtils.performAESDecryption(cipherText, key, initializationVector);
		assertEquals(paintText, decryptedText);
	}
}
