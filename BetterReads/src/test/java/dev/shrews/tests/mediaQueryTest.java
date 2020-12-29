package dev.shrews.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
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
		ArrayList<Object[]>  list = (ArrayList<Object[]>) mediaDao.getByTagAndAvgRatingAndNumberOfRatings("Science as Magic", 1L, 85.0);
		//System.out.println(list);
		assertTrue(list != null);
	}

	@Test
	@Order(2)
	void testSecondQuery() {
		ArrayList<Object[]>  list = (ArrayList<Object[]>) mediaDao.getByTagAndAvgRatingAndNumberOfRatingsWithDateRange("Complex", 1L, 80.0, 
				LocalDate.parse("1981-11-01"), LocalDate.parse("1983-11-01"));
		System.out.println(list);
		for(int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i)[0]);
			System.out.println(list.get(i)[1]);
		}
		assertTrue(list != null);
	}


}
