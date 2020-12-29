package dev.shrews.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import dev.shrews.beans.*;
import dev.shrews.services.MediaService;
import dev.shrews.services.UserService;
import dev.shrews.services.UserTagService;

@RestController
@CrossOrigin(origins="http://localhost:4200", allowCredentials = "true")
@RequestMapping(path="/usertag")
public class UserTagController {
    private UserTagService userTagServ;
    private MediaService mediaServ;
    private UserService userServ;

    @Autowired
    public UserTagController(UserTagService uts, MediaService ms, UserService us) {
        userTagServ = uts;
        mediaServ = ms;
        userServ = us;
    }
    
    @PostMapping("/add")
    public ResponseEntity<UserTag> addUserTag(HttpSession session, @RequestParam("user_id") Integer uid, 
    		@RequestParam("media_id") Integer mid, @RequestParam("name") String name)  {
    	System.out.println("enter /add handler");
    	
        try {

        	UserTag ut = new UserTag();
        	Media m = mediaServ.getByMediaId(mid);
        	ut.setMedia(m);
        	User u = userServ.getUserById(uid);
        	ut.setUser(u);
        	ut.setTagName(name);
        	ut.setId(userTagServ.addUserTag(ut));
			return ResponseEntity.ok(ut);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(400).build();
		}
    }


}