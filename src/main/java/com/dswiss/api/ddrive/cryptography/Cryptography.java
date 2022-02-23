package com.dswiss.api.ddrive.cryptography;

import java.nio.ByteBuffer;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import com.dswiss.api.ddrive.exception.CryptoException;

public class Cryptography {
	
	private static final String secret = "secret phrase";

	private static final String ENCRYPT_ALGO = "AES/GCM/NoPadding";
	private static final int TAG_LENGTH_BIT = 128;
	private static final int IV_LENGTH_BYTE = 12;
	private static final int SALT_LENGTH_BYTE = 16;
	
	public static byte[] getRandomNonce(int numBytes) {
		byte[] nonce = new byte[numBytes];
		new SecureRandom().nextBytes(nonce);
		return nonce;
	}

	public static SecretKey getAESKeyFromPassword(char[] password, byte[] salt)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
		KeySpec spec = new PBEKeySpec(password, salt, 65536, 256);
		SecretKey secret = new SecretKeySpec(factory.generateSecret(spec).getEncoded(), "AES");
		return secret;
	}

	public static byte[] encrypt(byte[] file) {
		try {
			byte[] salt = getRandomNonce(SALT_LENGTH_BYTE);
			byte[] iv = getRandomNonce(IV_LENGTH_BYTE);
	
			SecretKey aesKeyFromPassword = getAESKeyFromPassword(secret.toCharArray(), salt);
	
			Cipher cipher = Cipher.getInstance(ENCRYPT_ALGO);
			cipher.init(Cipher.ENCRYPT_MODE, aesKeyFromPassword, new GCMParameterSpec(TAG_LENGTH_BIT, iv));
	
			byte[] cipherFile = cipher.doFinal(file);
			byte[] fileEncrypt = ByteBuffer.allocate(iv.length + salt.length + cipherFile.length).put(iv).put(salt).put(cipherFile).array();
	
			return fileEncrypt;
		} catch (Exception ex) {
			throw new CryptoException("Encrypt process error");
		}
	}

	public static byte[] decrypt(byte[] fileEncrypt) {
		try {
			ByteBuffer bb = ByteBuffer.wrap(fileEncrypt);
			byte[] iv = new byte[IV_LENGTH_BYTE];
			bb.get(iv);
	
			byte[] salt = new byte[SALT_LENGTH_BYTE];
			bb.get(salt);
	
			byte[] cipherText = new byte[bb.remaining()];
			bb.get(cipherText);
	
			SecretKey aesKeyFromPassword = getAESKeyFromPassword(secret.toCharArray(), salt);
	
			Cipher cipher = Cipher.getInstance(ENCRYPT_ALGO);
			cipher.init(Cipher.DECRYPT_MODE, aesKeyFromPassword, new GCMParameterSpec(TAG_LENGTH_BIT, iv));
	
			byte[] file = cipher.doFinal(cipherText);
	
			return file;
		} catch (Exception ex) {
			throw new CryptoException("Decrypt process error");
		}
	}
}