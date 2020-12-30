package dev.shrews.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import dev.shrews.beans.Genre;
import dev.shrews.beans.Media;
import dev.shrews.beans.MediaType;
import dev.shrews.beans.Review;
import dev.shrews.data.MediaDAO;
import dev.shrews.data.MediaHibernate;

@TestMethodOrder(OrderAnnotation.class)
class mediaQueryTest {
	
	private static MediaDAO mediaDao;

	@BeforeAll public static void setUp(){
		mediaDao = new MediaHibernate();
	}

	@Test
	@Order(1)
	void testFirstQuery() {
		ArrayList<Object[]>  list = (ArrayList<Object[]>) mediaDao.getByTagAndAvgRatingAndNumberOfRatings("Science as Magic", 1L, 50.0);
		System.out.println(list);
		for(int i = 0; i < list.size(); i++) {
			System.out.println("i = " + i);
			System.out.println(list.get(i)[0]);
			System.out.println(list.get(i)[1]);
			System.out.println(list.get(i)[2]);
			System.out.println(list.get(i)[3]);
		}
		assertTrue(list != null);
	}
	

	@Test
	@Order(2)
	void testSecondQuery() {
		ArrayList<Object[]>  list = (ArrayList<Object[]>) mediaDao.getByTagAndAvgRatingAndNumberOfRatingsWithDateRange("Complex", 1L, 50.0, 
				LocalDate.parse("1981-11-01"), LocalDate.parse("1983-11-01"));
		System.out.println(list);
		for(int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i)[0]);
			System.out.println(list.get(i)[1]);
		}
		assertTrue(list != null);
	}

	@Test
	@Order(3)
	void testThirdQuery() {
		ArrayList<Object[]>  list = (ArrayList<Object[]>) mediaDao.getByGenreAndTagAndAvgRatingAndNumberOfRatings(1, "Science as Magic", 1L, 52.0);
		//System.out.println(list);
		assertTrue(list != null);
	}

	@Test
	@Order(4)
	void testFourthQuery() {
		ArrayList<Object[]>  list = (ArrayList<Object[]>) mediaDao.getByGenreAndTagAndAvgRatingAndNumberOfRatingsWithDateRange(1, "Complex", 1L, 50.0, 
				LocalDate.parse("1981-11-01"), LocalDate.parse("1983-11-01"));
		System.out.println(list);
		for(int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i)[0]);
			System.out.println(list.get(i)[1]);
		}
		assertTrue(list != null);
	}
	
	@Test
	@Order(5)
	void testFifthQuery() {
		ArrayList<Object[]>  list = (ArrayList<Object[]>) mediaDao.getByTagAndNotTagAndAvgRatingAndNumberOfRatings("Complex", "Science as Magic", 1L, 0.0);
		System.out.println(list);
		for(int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i)[0]);
			System.out.println(list.get(i)[1]);
		}
		assertTrue(list != null);
	}
	
	@Test
	@Order(6)
	void testSixthQuery() {
		ArrayList<Object[]>  list = (ArrayList<Object[]>) mediaDao.getByTagAndNotTagAndAvgRatingAndNumberOfRatingsWithDateRange("Complex", "Science as Magic", 1L, 0.0, 
				LocalDate.parse("1983-01-01"), LocalDate.parse("1983-11-01"));
		System.out.println(list);
		for(int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i)[0]);
			System.out.println(list.get(i)[1]);
		}
		assertTrue(list != null);
	}
	
	@Test
	@Order(7)
	void testSeventhQuery() {
		ArrayList<Object[]>  list = (ArrayList<Object[]>) mediaDao.getByGenreAndTagAndNotTagAndAvgRatingAndNumberOfRatings(1, "Complex", 
				"Science as Magic", 1L, 0.0 );
		System.out.println(list);
		for(int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i)[0]);
			System.out.println(list.get(i)[1]);
		}
		assertTrue(list != null);
	}
	
	@Test
	@Order(8)
	void testEighthQuery() {
		ArrayList<Object[]>  list = (ArrayList<Object[]>) mediaDao.getByGenreAndTagAndNotTagAndAvgRatingAndNumberOfRatingsWithDateRange(1, "Complex", "Science as Magic", 1L, 0.0, 
				LocalDate.parse("1983-01-01"), LocalDate.parse("1983-11-01"));
		System.out.println(list);
		for(int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i)[0]);
			System.out.println(list.get(i)[1]);
		}
		assertTrue(list != null);
	}
	
	@Test
	@Order(9)
	void testNinthQuery() {
		ArrayList<Object[]>  list = (ArrayList<Object[]>) mediaDao.getByGenreAndAvgRatingAndNumberOfRatings(1,1L, 0.0);
		System.out.println(list);
		for(int i = 0; i < list.size(); i++) {
			System.out.println("i = " + i);
			System.out.println(list.get(i)[0]);
			System.out.println(list.get(i)[1]);
		}
		assertTrue(list != null);
	}
	
	@Test
	@Order(10)
	void testTenthQuery() {
		ArrayList<Object[]>  list = (ArrayList<Object[]>) mediaDao.getByGenreAndAvgRatingAndNumberOfRatingsWithDateRange(1, 1L, 0.0, 
				LocalDate.parse("1983-01-01"), LocalDate.parse("1983-11-01"));
		System.out.println(list);
		for(int i = 0; i < list.size(); i++) {
			System.out.println("i = " + i);
			System.out.println(list.get(i)[0]);
			System.out.println(list.get(i)[1]);
		}
		assertTrue(list != null);
	}

	@Test
	@Order(11)
	void testEleventhQuery() {
		Set<Media> mset = mediaDao.getLikeTitle("Sword");
//		System.out.println(mset);

		Set<Media> mset2 = mediaDao.getLikeTitle("Sword");
//		System.out.println(mset2);
//		
		Set<Media> mset3 = new HashSet<Media>();
		mset3.addAll(mset);
		mset3.addAll(mset2);
		System.out.println(mset3);
		assertTrue(mset.size() > 0);
	}
}
