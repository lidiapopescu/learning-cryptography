package com.lidia.crypto.symmetric;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.security.KeyPair;

import javax.xml.bind.DatatypeConverter;

import org.junit.jupiter.api.Test;

import com.lidia.crypto.asymmetric.AsymmetricEncryptionUtils;

public class AsymmetricEncryptionUtilsTest {

	@Test
	void generateRSAKeyPair() throws Exception {
		KeyPair keyPair = AsymmetricEncryptionUtils.generateRSAkeyPair();
		assertNotNull(keyPair);
		System.out.println("Private key: " + DatatypeConverter.printHexBinary(keyPair.getPrivate().getEncoded()));
		System.out.println("Public  key: " + DatatypeConverter.printHexBinary(keyPair.getPublic().getEncoded()));
	}
	
	@Test
	void testRSACryptoRoutine() throws Exception {
		KeyPair keyPair = AsymmetricEncryptionUtils.generateRSAkeyPair();
		String plaintText = "This is the text we are going to hide in plain sight";
		byte[] cipherText = AsymmetricEncryptionUtils.performRSAEncryption(plaintText, keyPair.getPrivate());
		assertNotNull(cipherText);
		System.out.println(DatatypeConverter.printHexBinary(cipherText));
		String decryptText = AsymmetricEncryptionUtils.performRSADecryption(cipherText, keyPair.getPublic());
		assertEquals(plaintText, decryptText);
	}
}
