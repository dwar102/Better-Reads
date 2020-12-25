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
import dev.shrews.services.UserService;

@RestController
@RequestMapping(path="/media/comments")
@CrossOrigin(origins="http://localhost:4200", allowCredentials = "true")

public class MediaCommentsController {
    private UserService userServ;

    @Autowired
    public MediaCommentsController(UserService u) {
        userServ = u;
    }


    @GetMapping(path="{id}")
    @ResponseBody
    public ResponseEntity<Set<User_Media_Comments>> getComments(HttpSession session, @PathVariable("id") Integer id)  {
    	System.out.println("Reached");
        Set<User_Media_Comments> mediaComments = userServ.getCommentsForMedia(id);
        if (mediaComments != null) {
            return ResponseEntity.ok(mediaComments);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}