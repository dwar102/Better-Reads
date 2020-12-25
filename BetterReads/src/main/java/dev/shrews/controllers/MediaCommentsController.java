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
import org.springframework.web.bind.annotation.RestController;

import dev.shrews.beans.*;
import dev.shrews.services.UserService;

@RestController
@CrossOrigin(origins="http://localhost:4200", allowCredentials = "true")
@RequestMapping(path="/media/comments")
public class MediaCommentsController {
    private UserService userServ;

    @Autowired
    public MediaCommentsController(UserService u) {
        userServ = u;
    }


    @GetMapping("/{id}")
    public ResponseEntity<Set<User_Media_Comments>> getComments(HttpSession session, @RequestParam("id") Integer id)  {
        Set<User_Media_Comments> mediaComments = userServ.getCommentsForMedia(id);
        if (mediaComments != null) {
            return ResponseEntity.ok(mediaComments);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}