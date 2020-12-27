package dev.shrews.controllers;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import dev.shrews.beans.User;
import dev.shrews.services.UserService;
import dev.shrews.services.UserServiceImpl;
import dev.shrews.exceptions.NonUniqueUsernameException;

@RestController
@CrossOrigin(origins="http://localhost:4200", allowCredentials="true")
@RequestMapping(path="/users")
public class UserController {

	private static UserService userService;
	private static String algorithm = "PBKDF2WithHmacSHA1"; // hashing algorithm
	private static int derivedKeyLength = 160; // for SHA1
    private static int iterations = 20000; // NIST specifies 10000
    
    @Autowired
    public UserController(UserService u) {
    	userService = u;
    }
    
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
    
    @GetMapping
	public ResponseEntity<User> checkLogin(HttpSession session) {
		User loggedUser = (User) session.getAttribute("user");
		if (loggedUser == null)
			return ResponseEntity.badRequest().build();
		return ResponseEntity.ok(loggedUser);
	}
    
    @PutMapping
    public ResponseEntity<User> logIn(HttpSession session, @RequestParam("user") String username,
			@RequestParam("pass") String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
		
    	User user = userService.getUserByName(username);
    	
    	if (user != null) {
    		if (user.getPass().equals(getHash(password, user.getSalt()))) {
    			session.setAttribute("user", user);
    			return ResponseEntity.ok(user);
    		}
    		return ResponseEntity.badRequest().build();
    	}
    	return ResponseEntity.notFound().build();
	}
    
    @PostMapping()
    public ResponseEntity<User> registerUser(HttpSession session, @RequestBody() String body) throws NoSuchAlgorithmException, InvalidKeySpecException, JsonParseException, JsonMappingException, IOException, NonUniqueUsernameException {
    	ObjectMapper mapper = new ObjectMapper();
    	ObjectNode node = mapper.readValue(body, ObjectNode.class);
    	User registerUser = new User();
    	
    	registerUser.setUsername(node.get("user").toString().replace("\"", ""));
    	registerHash(registerUser, node.get("pass").toString().replace("\"", ""));
    	
    	User user = userService.addUser(registerUser);
    	
    	if (user != null) {
    		return ResponseEntity.ok().build();
    	} else {
    		return ResponseEntity.badRequest().build();
    	}
    }
    
    @DeleteMapping
	public ResponseEntity<Void> logOut(HttpSession session) {
		session.invalidate();
		return ResponseEntity.ok().build();
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