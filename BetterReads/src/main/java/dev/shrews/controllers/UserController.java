package dev.shrews.controllers;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import dev.shrews.services.UserService;
import dev.shrews.services.UserServiceImpl;

public class UserController {

	private static UserService userService = new UserServiceImpl();
	private static String algorithm = "PBKDF2WithHmacSHA1"; // hashing algorithm
	private static int derivedKeyLength = 160; // for SHA1
    private static int iterations = 20000; // NIST specifies 10000
    
    /*public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException {
	
	String password = "password"; //Can change this to get random salt and hash based on the string
	
	//Generate a random salt
	SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
	byte[] salt = new byte[8];
    random.nextBytes(salt);
    String newSalt = Base64.getEncoder().encodeToString(salt);
    System.out.println(newSalt);
    
    //Generate password
    byte[] saltBytes = Base64.getDecoder().decode(newSalt);
    
    KeySpec spec = new PBEKeySpec(password.toCharArray(), saltBytes, iterations, derivedKeyLength);
    SecretKeyFactory f = SecretKeyFactory.getInstance(algorithm);
	
    byte[] encBytes = f.generateSecret(spec).getEncoded();
    String passHash = Base64.getEncoder().encodeToString(encBytes);
    System.out.println(passHash);
    System.out.println(passHash.length());
    }
	*/
}