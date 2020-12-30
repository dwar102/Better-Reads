package dev.shrews.controllers;
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
import dev.shrews.services.FriendshipService;
import dev.shrews.services.UserService;

@RestController
@RequestMapping(path="/friendships")
@CrossOrigin(origins="http://localhost:4200", allowCredentials = "true")
public class FriendshipsController {
    private FriendshipService friendServ;
    private UserService userServ;

    @Autowired
    public FriendshipsController(UserService u, FriendshipService f) {
        friendServ = f;
        userServ = u;
    }

    @GetMapping(path="{id}")
    @ResponseBody
    public ResponseEntity<Set<Friendships>> addFriendship(HttpSession session, @PathVariable("id") Integer id) {
        System.out.println("Reach get friendship");
        Set<Friendships> friendship = friendServ.getFriendshipsByUserId(id);
        if (friendship != null) {
            return ResponseEntity.ok(friendship);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping
    public ResponseEntity<Friendships> addFriendship(HttpSession session, @RequestBody Friendships friendship) {
        System.out.println("Reached PostFriendships");
        Integer id = friendship.getFriend_id().getId();
        User user = userServ.getUserById(id);
        if (user != null) {
            friendServ.addFriendships(friendship);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
