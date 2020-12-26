package dev.shrews.controllers;

import java.time.LocalDate;
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
import dev.shrews.services.MediaService;
import dev.shrews.services.UserService;

@RestController
@CrossOrigin(origins="http://localhost:4200", allowCredentials = "true")
@RequestMapping(path="/media")
public class MediaController {
    private MediaService mediaServ;

    @Autowired
    public MediaController(MediaService m) {
        mediaServ = m;
    }


    @PostMapping("/add")
    public ResponseEntity<Media> addMedia(HttpSession session, @RequestParam("creator") String creator, @RequestParam("date") CharSequence date,
    		@RequestParam("genre") Integer gid, @RequestParam("mediatype") Integer mtid, @RequestParam("title") String title)  {
    	MediaType mt = new MediaType();
    	mt.setId(mtid);
    	Genre g = new Genre();
    	g.setId(gid);
    	Media m = new Media();
    	m.setGenre(g);
    	m.setMediaType(mt);
    	m.setCreator(creator);
    	m.setTitle(title);
    	//System.out.println(date);
    	m.setDate(LocalDate.parse(date));
    	//System.out.println(m);
        try {
			Media m2 = mediaServ.addMedia(m);
	    	System.out.println("got here" + m);
			return ResponseEntity.ok(m2);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(400).build();
		}
    }
}