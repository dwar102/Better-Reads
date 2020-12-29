package dev.shrews.controllers;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import dev.shrews.beans.Media;
import dev.shrews.beans.Shelf;
import dev.shrews.beans.User;
import dev.shrews.services.MediaService;
import dev.shrews.services.ShelfService;
import dev.shrews.services.ShelfServiceImpl;
import dev.shrews.services.UserService;

@RestController
@CrossOrigin(allowCredentials="true")
@RequestMapping(path="/shelves")
public class shelfController {
    private ShelfService shelfServ;

    private MediaService mediaServ;

    @Autowired
    public shelfController(ShelfService u, MediaService m) {
        shelfServ = u;
        mediaServ = m;
    }
/*    @PutMapping
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
	}*/ 	
    @GetMapping
    @ResponseBody
	public ResponseEntity<Set<Shelf>> getShelves(HttpSession session, @RequestParam("user") String userId) {
		//Integer loggedUserId = (Integer) session.getAttribute("user");
		User loggedUser = new User();
		loggedUser.setId(Integer.parseInt(userId));
		Set<Shelf> shelves = shelfServ.getUserShelves(loggedUser);
		if (userId == null)
			return ResponseEntity.badRequest().build();
		return ResponseEntity.ok(shelves);
	}
	//BetterReads/shelves/assignments/?shelf=1
    @GetMapping("/assignments")
    @ResponseBody
	public ResponseEntity<Set<Media>> getShelfAssignments(HttpSession session, @RequestParam("shelf") String shelfId) {
		//Integer loggedUserId = (Integer) session.getAttribute("user");
		Shelf s = new Shelf();
		s.setId(Integer.parseInt(shelfId));
		Set<Media> shelfAssignments = shelfServ.getShelfAssignments(s);
		if (shelfId == null)
			return ResponseEntity.badRequest().build();
		return ResponseEntity.ok(shelfAssignments);
	}  
	
public static Shelf addShelf() {
	Shelf newShelf = new Shelf();
	
	/*
	 * this method will have the spring equivalent of a context passed in 
	 * 
	 * 1. details will be gathered from context to construct a Shelf object (must have userid set)
	 * 
	 * 2. pass shelf object to the shelf service
	 * 
	 * 3. set http response codes
	 * */
	
	return newShelf;
}

}
