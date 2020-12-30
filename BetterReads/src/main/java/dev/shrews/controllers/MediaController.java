package dev.shrews.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
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

@RestController
@CrossOrigin(origins="http://localhost:4200", allowCredentials = "true")
@RequestMapping(path="/media")
public class MediaController {
    private MediaService mediaServ;

    @Autowired
    public MediaController(MediaService m) {
        mediaServ = m;
    }

    @GetMapping(path="{id}")
    @ResponseBody
    public ResponseEntity<Media> viewMedia(HttpSession session, @PathVariable("id") Integer id){
    	System.out.println("enter /view handler");
    	try {
			return ResponseEntity.ok(mediaServ.getByMediaId(id));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(400).build();
		}
    }

    @PostMapping("/add")
    public ResponseEntity<Media> addMedia(HttpSession session, @RequestParam("creator") String creator, @RequestParam("date") CharSequence date,
    		@RequestParam("genre") Integer gid, @RequestParam("mediatype") Integer mtid, @RequestParam("title") String title)  {
    	System.out.println("enter /add handler");
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
	    	//System.out.println("got here" + m);
			return ResponseEntity.ok(m2);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(400).build();
		}
    }


    @GetMapping("/totalratings")
    @ResponseBody
    public ResponseEntity<Long> getTotalRatings(HttpSession session, @RequestParam("media_id") Integer id)  {
    	System.out.println("enter /totalratings handler");
        try {
    		return ResponseEntity.ok(mediaServ.getNumRatingsById(id));
        }
        catch(Exception e) {
			return ResponseEntity.status(400).build();
        }
    }


    @GetMapping("/avgrating")
    @ResponseBody
    public  ResponseEntity<Double> getAvgRating(HttpSession session, @RequestParam("media_id") Integer id)  {
    	System.out.println("enter /avgratings handler");
        try {
    		return ResponseEntity.ok(mediaServ.getAvgRatingById(id));
        }
        catch(Exception e) {
			return ResponseEntity.status(400).build();
        }
    }


    @GetMapping("/tagnames")
    @ResponseBody
    public ResponseEntity<List<String>> getTagnames(HttpSession session, @RequestParam("media_id") Integer id)  {
    	System.out.println("enter /tagnames handler");
        try {
    		return ResponseEntity.ok(mediaServ.getTagnamesById(id));
        }
        catch(Exception e) {
			return ResponseEntity.status(400).build();
        }
    }


    @GetMapping("/tagcount")
    @ResponseBody
    public ResponseEntity<List<Long>> getTagCount(HttpSession session, @RequestParam("media_id") Integer id)  {
    	System.out.println("enter /tagcount handler");
        try {
    		return ResponseEntity.ok(mediaServ.getTagCountById(id));
        }
        catch(Exception e) {
			return ResponseEntity.status(400).build();
        }
    }
    
    @PutMapping("/search")
    @ResponseBody
    public ResponseEntity<Set<Media>> searchMedia(HttpSession session, @RequestParam("type") String searchType, @RequestParam("content") String searchContent) {
    	System.out.println("Searching...");
    	try {
    		return ResponseEntity.ok(mediaServ.getSearch(searchType, searchContent));
    	} catch (Exception e) {
    		return ResponseEntity.status(400).build();
    	}
    }
    
    @GetMapping("/searchByGenreTagNotTagAvgRatingNumRatingDates")
    @ResponseBody
    public ResponseEntity<Media[]> searchfilter(HttpSession session, @RequestParam("genre") Integer gid, @RequestParam("tagName") String tagName,
    		 @RequestParam("notTagName") String notTagName, @RequestParam("minAvgRating") Double minAvgRating, @RequestParam("minNumRatings") Long minNumRatings,
    		 @RequestParam("minDate") CharSequence minDate, @RequestParam("maxDate") CharSequence maxDate){
    	try {
			Media[] marray = mediaServ.getByGenreTagnAndNotTagAndAvgRatingAndNumRatingWithDates(gid, tagName, notTagName, minNumRatings, minAvgRating, 
					LocalDate.parse(minDate), LocalDate.parse(maxDate));
			return ResponseEntity.ok(marray);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(400).build();
		}
    }
    
    @GetMapping("/searchByGenreTagAvgRatingNumRatingDates")
    @ResponseBody
    public ResponseEntity<Media[]> searchfilter(HttpSession session, @RequestParam("genre") Integer gid, @RequestParam("tagName") String tagName,
    		 @RequestParam("minAvgRating") Double minAvgRating, @RequestParam("minNumRatings") Long minNumRatings,
    		 @RequestParam("minDate") CharSequence minDate, @RequestParam("maxDate") CharSequence maxDate){
    	try {
			Media[] marray = mediaServ.getByGenreTagnameAvgRatingNumRatingWithDates(gid, tagName, minNumRatings, minAvgRating, 
					LocalDate.parse(minDate), LocalDate.parse(maxDate));
			return ResponseEntity.ok(marray);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(400).build();
		}
    }
    
    @GetMapping("/searchByGenreTagAvgRatingNumRating")
    @ResponseBody
    public ResponseEntity<Media[]> searchfilter(HttpSession session, @RequestParam("genre") Integer gid, @RequestParam("tagName") String tagName,
    		 @RequestParam("minAvgRating") Double minAvgRating, @RequestParam("minNumRatings") Long minNumRatings){
    	try {
			Media[] marray = mediaServ.getByGenreTagnameAvgRatingNumRating(gid, tagName, minNumRatings, minAvgRating);
			return ResponseEntity.ok(marray);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(400).build();
		}
    }
    
    @GetMapping("/searchByGenreTagNotTagAvgRatingNumRating")
    @ResponseBody
    public ResponseEntity<Media[]> searchfilter(HttpSession session, @RequestParam("genre") Integer gid, @RequestParam("tagName") String tagName,
    		 @RequestParam("notTagName") String notTagName, @RequestParam("minAvgRating") Double minAvgRating, @RequestParam("minNumRatings") Long minNumRatings){
    	try {
			Media[] marray = mediaServ.getByGenreTagnAndNotTagAndAvgRatingAndNumRating(gid, tagName, notTagName, minNumRatings, minAvgRating);
			return ResponseEntity.ok(marray);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(400).build();
		}
    }
    
    @GetMapping("/searchByTagNotTagAvgRatingNumRatingDates")
    @ResponseBody
    public ResponseEntity<Media[]> searchfilter(HttpSession session, @RequestParam("tagName") String tagName,
    		 @RequestParam("notTagName") String notTagName, @RequestParam("minAvgRating") Double minAvgRating, @RequestParam("minNumRatings") Long minNumRatings,
    		 @RequestParam("minDate") CharSequence minDate, @RequestParam("maxDate") CharSequence maxDate){
    	try {
			Media[] marray = mediaServ.getByTagnAndNotTagAndAvgRatingAndNumRatingWithDates(tagName, notTagName, minNumRatings, minAvgRating, 
					LocalDate.parse(minDate), LocalDate.parse(maxDate));
			return ResponseEntity.ok(marray);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(400).build();
		}
    }
    
    @GetMapping("/searchByTagAvgRatingNumRatingDates")
    @ResponseBody
    public ResponseEntity<Media[]> searchfilter(HttpSession session,  @RequestParam("tagName") String tagName,
    		 @RequestParam("minAvgRating") Double minAvgRating, @RequestParam("minNumRatings") Long minNumRatings,
    		 @RequestParam("minDate") CharSequence minDate, @RequestParam("maxDate") CharSequence maxDate){
    	try {
			Media[] marray = mediaServ.getByTagnameAvgRatingNumRatingWithDates(tagName, minNumRatings, minAvgRating, 
					LocalDate.parse(minDate), LocalDate.parse(maxDate));
			return ResponseEntity.ok(marray);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(400).build();
		}
    }
    
    @GetMapping("/searchByTagAvgRatingNumRating")
    @ResponseBody
    public ResponseEntity<Media[]> searchfilter(HttpSession session,  @RequestParam("tagName") String tagName,
    		 @RequestParam("minAvgRating") Double minAvgRating, @RequestParam("minNumRatings") Long minNumRatings){
    	try {
			Media[] marray = mediaServ.getByTagnameAvgRatingNumRating(tagName, minNumRatings, minAvgRating);
			return ResponseEntity.ok(marray);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(400).build();
		}
    }
    
    @GetMapping("/searchByTagNotTagAvgRatingNumRating")
    @ResponseBody
    public ResponseEntity<Media[]> searchfilter(HttpSession session,  @RequestParam("tagName") String tagName,
    		 @RequestParam("notTagName") String notTagName, @RequestParam("minAvgRating") Double minAvgRating, @RequestParam("minNumRatings") Long minNumRatings){
    	try {
			Media[] marray = mediaServ.getByTagnAndNotTagAndAvgRatingAndNumRating(tagName, notTagName, minNumRatings, minAvgRating);
			return ResponseEntity.ok(marray);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(400).build();
		}
    }
    
    @GetMapping("/searchByGenreAvgRatingNumRating")
    @ResponseBody
    public ResponseEntity<Media[]> searchfilter(HttpSession session, @RequestParam("genre") Integer gid,
    		@RequestParam("minAvgRating") Double minAvgRating,  @RequestParam("minNumRatings") Long minNumRatings){
    	try {
			Media[] marray = mediaServ.getByGenreAvgRatingNumRating(gid, minNumRatings, minAvgRating);
			return ResponseEntity.ok(marray);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(400).build();
		}
    }
    
    @GetMapping("/searchByGenreAvgRatingNumRatingDates")
    @ResponseBody
    public ResponseEntity<Media[]> searchfilter(HttpSession session, @RequestParam("genre") Integer gid,
    		@RequestParam("minAvgRating") Double minAvgRating,  @RequestParam("minNumRatings") Long minNumRatings,
   		 @RequestParam("minDate") CharSequence minDate, @RequestParam("maxDate") CharSequence maxDate){
    	try {
			Media[] marray = mediaServ.getByGenreAvgRatingNumRatingWithDates(gid, minNumRatings, minAvgRating, 
					LocalDate.parse(minDate), LocalDate.parse(maxDate));
			return ResponseEntity.ok(marray);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(400).build();
		}
    }
    
}