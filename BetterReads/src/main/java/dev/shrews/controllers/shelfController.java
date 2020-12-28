package dev.shrews.controllers;

import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import dev.shrews.beans.Shelf;
import dev.shrews.beans.User;
import dev.shrews.services.MediaService;
import dev.shrews.services.ShelfService;
import dev.shrews.services.ShelfServiceImpl;
import dev.shrews.services.UserService;

@RestController
@CrossOrigin(origins="http://localhost:4200", allowCredentials="true")
@RequestMapping(path="/shelves")
public class shelfController {
    private ShelfService shelfServ;

    private MediaService mediaServ;
    
    @Autowired
    public shelfController(ShelfService u, MediaService m) {
        shelfServ = u;
        mediaServ = m;
    }

    @GetMapping
    @ResponseBody
	public ResponseEntity<Set<Shelf>> getShelves(HttpSession session) {
		User loggedUser = (User) session.getAttribute("user");
		Set<Shelf> shelves = shelfServ.getShelves();
		if (loggedUser == null)
			return ResponseEntity.badRequest().build();
		return ResponseEntity.ok(shelves);
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
