package dev.shrews.controllers;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import dev.shrews.beans.Media;
import dev.shrews.beans.Messages;
import dev.shrews.beans.Shelf;
import dev.shrews.beans.ShelfAssignment;
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
    
    
    @PostMapping("/assignments/add")
    public ResponseEntity<ShelfAssignment> addMessage(HttpSession session, @RequestBody ShelfAssignment sa) {
    	System.out.println("reached PostMapping /assignments/add)");
        Shelf shelf = shelfServ.getShelf(sa.getShelf());
        
        if (shelf != null) {
        	sa.setUser(shelf.getUser());
        	sa.setMedia(mediaServ.getByMediaId(sa.getMedia().getId()));
            shelfServ.addShelfAssignment(sa);
            return ResponseEntity.ok(sa);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    //'http://localhost:8080/BetterReads/shelves/assignments';
    @DeleteMapping("/assignments")
    @ResponseBody
	public ResponseEntity<Set<ShelfAssignment>> deleteShelfAssignments(HttpSession session, @RequestParam("assgn") String assgn) {
		System.out.println("reached /assignments delete handler");
    	//Integer loggedUserId = (Integer) session.getAttribute("user");
		ShelfAssignment s = new ShelfAssignment();
		s.setId(Integer.parseInt(assgn));
		ShelfAssignment sa = shelfServ.getShelfAssignmentById(s);
		
		if (sa == null)
			return ResponseEntity.badRequest().build();
	    //Set<ShelfAssignment> items = new HashSet<>();
	    
	    
	    //for (ShelfAssignment e : shelfAssignments)
	       // items.add(e);
		else {
			shelfServ.deleteShelfAssignment(sa);
			return ResponseEntity.ok().build();
		}
	}  
    
	//BetterReads/shelves/assignments/?shelf=1
    @GetMapping("/assignments")
    @ResponseBody
	public ResponseEntity<Set<ShelfAssignment>> getShelfAssignments(HttpSession session, @RequestParam("shelf") String shelfId) {
		System.out.println("reached /assignments handler");
    	//Integer loggedUserId = (Integer) session.getAttribute("user");
		Shelf s = new Shelf();
		s.setId(Integer.parseInt(shelfId));
		List<ShelfAssignment> shelfAssignments = shelfServ.getShelfAssignments(s);
		if (shelfId == null)
			return ResponseEntity.badRequest().build();
	    Set<ShelfAssignment> items = new HashSet<>();
	    
	    
	    for (ShelfAssignment e : shelfAssignments)
	        items.add(e);
		return ResponseEntity.ok(items);
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
 