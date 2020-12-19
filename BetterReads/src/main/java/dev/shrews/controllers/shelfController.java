package dev.shrews.controllers;

import dev.shrews.beans.Shelf;

public class shelfController {
public static Shelf addShelf() {
	Shelf newShelf = new Shelf();
	
	/*
	 * this method will have the spring equivalent of a context passed in 
	 * 
	 * 1. details will be gathered from context to construct a Shelf object (must have userid set)
	 * 
	 * 2. pass shelf object to the shelf service
	 * 
	 * 3. set http response codes
	 * */
	
	return newShelf;
}
}
