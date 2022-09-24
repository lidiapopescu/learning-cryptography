package com.lidia.crypto.keystore;

import java.security.KeyStore;

import javax.crypto.SecretKey;

public class KeyStoreUtils {

//	The reason we are creating a specific type of a KeyStore is because we are saving a private key.
//	and the standard default KeyStore in Java will not store a private key 
	private static final String SECRET_KEY_KEYSTORE_TYPE= "JCEKS";
	
//	 we could use one password for both, but better to keep separate
	public static KeyStore createPrivateKeyJavaKeyStore (String keystorePassword, String keysAlias, SecretKey secretKey, String secretKeyPassword) throws Exception {
		
		KeyStore keyStore = KeyStore.getInstance(SECRET_KEY_KEYSTORE_TYPE);
		
//		In order to manipulate java keyStore in memory, you actually have to load it
//		but since we're gonna use memory to do our loading we're gonna send a null input String
		keyStore.load(null, keystorePassword.toCharArray());
		
//		We need to store the password for the actual key itself
		KeyStore.ProtectionParameter entryPassword = new KeyStore.PasswordProtection(secretKeyPassword.toCharArray());
		KeyStore.SecretKeyEntry privateKeyEntry = new KeyStore.SecretKeyEntry(secretKey);
		keyStore.setEntry(keysAlias, privateKeyEntry, entryPassword);
		return keyStore;
	}
}
