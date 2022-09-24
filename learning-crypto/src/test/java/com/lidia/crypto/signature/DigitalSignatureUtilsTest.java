package com.lidia.crypto.signature;

import static org.junit.jupiter.api.Assertions.*;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyPair;

import javax.xml.bind.DatatypeConverter;

import org.junit.jupiter.api.Test;

import com.lidia.crypto.asymmetric.AsymmetricEncryptionUtils;

class DigitalSignatureUtilsTest {

	@Test
	void digitalSignatureRoutine() throws Exception {
		URL uri = this.getClass().getClassLoader().getResource("demo.txt");
		Path path = Paths.get(uri.toURI());
		byte[] input = Files.readAllBytes(path);
		
		KeyPair keyPair = AsymmetricEncryptionUtils.generateRSAkeyPair();
		byte[] signature = DigitalSignatureUtils.createDigitalSignature(input, keyPair.getPrivate());
		System.out.println(DatatypeConverter.printHexBinary(signature));
		assertTrue(DigitalSignatureUtils.verifyDigitalSignature(input, signature, keyPair.getPublic()));
	}
}
