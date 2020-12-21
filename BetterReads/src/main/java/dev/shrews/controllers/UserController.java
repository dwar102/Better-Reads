package dev.shrews.controllers;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import dev.shrews.beans.User;
import dev.shrews.services.UserService;
import dev.shrews.services.UserServiceImpl;
import dev.shrews.exceptions.NonUniqueUsernameException;
import io.javalin.http.Context;

public class UserController {

	private static UserService userService = new UserServiceImpl();
	private static String algorithm = "PBKDF2WithHmacSHA1"; // hashing algorithm
	private static int derivedKeyLength = 160; // for SHA1
    private static int iterations = 20000; // NIST specifies 10000
    
    /*public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException {
	
	String password = "pass"; //Can change this to get random salt and hash based on the string
	
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
    }*/
    
public static void logIn(Context ctx) throws NoSuchAlgorithmException, InvalidKeySpecException {
		
		System.out.println("Logging in");
		String username = ctx.queryParam("user");
		User p = userService.getUserByName(username);
		
		if (p != null) {
			
			String password = getHash(ctx.queryParam("pass"), p.getSalt());
			
			if (p.getPass().equals(password))
			{
				System.out.println("Logged in as " + p.getUsername());
				ctx.status(200);
				ctx.json(p);
				ctx.sessionAttribute("user", p);
			}
			else
			{
				// password mismatch
				ctx.status(400);
			}
		}
		else
		{
			// username not found
			ctx.status(404);
		}
		
	}

    public static void registerUser(Context ctx) throws NoSuchAlgorithmException, InvalidKeySpecException {
    	
    	//TODO: make sure username an any additional information is passed and stored correctly before calling addUser
    	String password = ctx.body();
    	User newUser = new User();
    	
    	registerHash(newUser, password);
    	
		try {
			userService.addUser(newUser);
		}
		catch (NonUniqueUsernameException e) {
			System.out.println("Username already taken :(");
			ctx.status(409); // 409 = conflict
		}
		ctx.status(200);
    	
    }
    
    //Used to generate a unique salt and hashed password to store in database when a user registers an account
    public static void registerHash(User user, String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
    	
    	SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
    	byte[] salt = new byte[8];
        random.nextBytes(salt);
        user.setSalt(Base64.getEncoder().encodeToString(salt));
        
        //Generate password
        byte[] saltBytes = Base64.getDecoder().decode(user.getSalt());
        
        KeySpec spec = new PBEKeySpec(password.toCharArray(), saltBytes, iterations, derivedKeyLength);
        SecretKeyFactory f = SecretKeyFactory.getInstance(algorithm);
    	
        byte[] encBytes = f.generateSecret(spec).getEncoded();
        user.setPass(Base64.getEncoder().encodeToString(encBytes));
        
    }
    
    //Returns the hashed password given the plaintext password and salt drawn from the database
    public static String getHash(String password, String salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
    	
    	byte[] saltBytes = Base64.getDecoder().decode(salt);
		KeySpec spec = new PBEKeySpec(password.toCharArray(), saltBytes, iterations, derivedKeyLength);
		SecretKeyFactory f = SecretKeyFactory.getInstance(algorithm);
		byte[] encBytes = f.generateSecret(spec).getEncoded();
		return Base64.getEncoder().encodeToString(encBytes);
    	
    }
    
}