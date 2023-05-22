package io.extio.security;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.spec.PKCS8EncodedKeySpec;
import javax.crypto.Cipher;
import org.apache.commons.io.IOUtils;


/**
 * @author jpilla
 * @since 17th Nov, 2022
 * @implNote To decrypt card details for "Show Encrypted Card Data API"
 */
public class X509CardDecryptionService {


		private final static String privateKey = "/Users/jpilla/Downloads/dtbk-pts-dc-show-card-private.pem";

	
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		String encryptedCardData = "KeqQ+PHTflgTdJV0qAMxxS53YDtXtd+F5evLdb185Ilx0UyRcKJwbWjsI7ExNJISbDTlAincu3ofLZacNB8DHeXtMPfUgcJbRouVdKm+Oe9ltpfl9FST5hQk1coJflMF4/SDOCdxlwLFwNZ+X8By6UL+klhCq4Q71IyC3kcloffe3ZEZmMmPmjOL5FH61VAfPlNezUxEhBzFerIu+8/HVV7jprtvF/5D/1HQpxkqPkI0vQchYoca2xIEA+pQyH7F8602wKDzNgC5TR6PdxoZ9KHQ/JwlyoG2ONpi8WqAZMb6s7hJ6rO+vgp1qSq7rTpJYBrscPOFc34VABY4NA48KA=="; 

		try {
			
			String decryptedData		= certDecrypt(encryptedCardData, privateKey);
			System.out.println("### Decrypted Card Data ---> " + decryptedData);


		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	
	

	 public static String certDecrypt(String cipherText, String privateKeyPath)
	            throws IOException, GeneralSecurityException {

	        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(IOUtils.toByteArray(new FileInputStream(privateKeyPath)));

	        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
	        cipher.init(Cipher.DECRYPT_MODE, KeyFactory.getInstance("RSA").generatePrivate(pkcs8EncodedKeySpec));

	        return new String(cipher.doFinal(org.bouncycastle.util.encoders.Base64.decode(cipherText.getBytes())), "UTF-8");
	    }
	
	

}
