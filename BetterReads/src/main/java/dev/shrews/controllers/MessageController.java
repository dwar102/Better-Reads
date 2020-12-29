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
import dev.shrews.services.MessagesService;
import dev.shrews.services.UserService;

@RestController
@RequestMapping(path="/messages/")
@CrossOrigin(origins="http://localhost:4200", allowCredentials = "true")

public class MessageController {
    private UserService userServ;

    private MessagesService messageServ;
    
    @Autowired
    public MessageController(UserService u, MessagesService m) {
        userServ = u;
        messageServ = m;
    }



    @GetMapping(path="{id}")
    @ResponseBody
    public ResponseEntity<Set<Messages>> getMessages(HttpSession session, @PathVariable("id") Integer id)  {
    	System.out.println("Reached");
        Set<Messages> userMessages = messageServ.getMessagesByUserId(id);
        if (userMessages != null) {
            return ResponseEntity.ok(userMessages);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping
    public ResponseEntity<Messages> addMessage(HttpSession session, @RequestBody Messages message) {
        System.out.println("Reached addMessage");
        Integer id = message.getRecipient().getId();
        User user = userServ.getUserById(id);
        if (user != null) {
            messageServ.addMessages(message);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}