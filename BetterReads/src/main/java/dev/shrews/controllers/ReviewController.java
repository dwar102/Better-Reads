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
import dev.shrews.services.ReviewService;
import dev.shrews.services.UserService;
import dev.shrews.services.MediaService;

@RestController
@RequestMapping(path="/reviews/")
@CrossOrigin(origins="http://localhost:4200", allowCredentials = "true")

public class ReviewController {
    private UserService userServ;

    private MediaService mediaServ;

    private ReviewService reviewServ;
    
    @Autowired
    public ReviewController(UserService u, ReviewService m, MediaService n) {
        userServ = u;
        reviewServ = m;
        mediaServ = n;
    }



    @GetMapping(path="{id}")
    @ResponseBody
    public ResponseEntity<Set<Review>> getReviews(HttpSession session, @PathVariable("id") Integer id)  {
    	System.out.println("Reached");
        Set<Review> reviews = reviewServ.getByMediaId(id);
        if (reviews != null) {
            return ResponseEntity.ok(reviews);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping
    public ResponseEntity<Review> addComment(HttpSession session, @RequestBody Review review) {
        System.out.println("Reached PutComment");
        Integer id = review.getMedia().getId();
        Media media = mediaServ.getByMediaId(id);
        if (media != null) {
            reviewServ.addReview(review);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

