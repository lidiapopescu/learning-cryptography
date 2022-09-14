package com.lidia.crypto;

import java.security.NoSuchAlgorithmException;

/**
 *
 *
 */
public class App 
{
    public static void main( String[] args ) {
    	try {
			//It returns 128, in case of limited policy files.
			// On the other hand, in case it returns 2147483647 then the JCE uses unlimited policy files.
			int maxKeySize = javax.crypto.Cipher.getMaxAllowedKeyLength("AES");
			System.out.println(maxKeySize);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
    }
}
