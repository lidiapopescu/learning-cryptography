package com.lidia.crypto.hash;

import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;

import javax.xml.bind.DatatypeConverter;

import org.junit.jupiter.api.Test;

class HashUtilsTest {

	@Test
	void generateRandomSalt() {
		byte[] salt = HashUtils.generateRandomSalt();
		assertNotNull(salt);
		System.out.println(DatatypeConverter.printHexBinary(salt));
	}
	
	@Test
	void createSHA2Hash() throws Exception {
		byte[] salt = HashUtils.generateRandomSalt();
		String valueToHash = UUID.randomUUID().toString();
		byte[] hash = HashUtils.createSHA2Hash(valueToHash, salt);
		assertNotNull(hash);
		byte[] hash2 =  HashUtils.createSHA2Hash(valueToHash, salt);
		assertEquals(DatatypeConverter.printHexBinary(hash), DatatypeConverter.printHexBinary(hash2));
	}
	
	@Test
	void testPasswordRoutine() {
		String secretPhrase = "correct horse battery staple";
		String passwordHash = HashUtils.hashPassword(secretPhrase);
		System.out.println(passwordHash);
		assertTrue(HashUtils.verifyPassword(secretPhrase, passwordHash));
	}
}
