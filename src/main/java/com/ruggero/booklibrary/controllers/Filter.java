package com.ruggero.booklibrary.controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.ruggero.booklibrary.entities.Book;
import com.ruggero.booklibrary.service.Util;


@RestController
public class Filter {
	
	
	
	/* Rest API endpoint to list all the books.*/
	@RequestMapping(value= "visma/books/" , method=RequestMethod.GET)
	 public List<Book> retrieveAllBooks() throws IOException {
		List<Book> bookList = new ArrayList<Book>();
		 /*****/
		try {
			bookList = Util.retrieve();
		} catch(FileNotFoundException e) {
			System.out.println("Non existing file");
			File file = new File (Util.getFilePath());
			file.createNewFile();
			if(file.exists())
				
			Util.initializeArchiveFile();
				
			System.out.println("A new storage file was  created!");
            
	 }
		return  bookList;
	}
	
    /** Filter by author
    A client can use a REST call to retrieve a list of books by providing author 
	 * @throws IOException */
	@RequestMapping(value = "visma/books/filterByAuthor/{author}", method = RequestMethod.GET)
	public List<Book> getBookByAuthor(@PathVariable("author") String author) throws IOException {
		List<Book> internalBookList = new ArrayList<Book>();
		List<Book> bookList = Util.retrieve();
		for(Book book : bookList )
			if(book.getAuthor().equals(author)) internalBookList.add(book);
		return internalBookList;
	}
	
	
	/**Filter by category
	* A client can use a REST call to retrieve a list of books by providing category 
	 * @throws IOException */
	@RequestMapping(value = "visma/books/filterByCategory/{category}", method = RequestMethod.GET)
	public List<Book> getBookByCategory(@PathVariable("category") String category) throws IOException {
		List<Book> internalBookList = new ArrayList<Book>();
		
		List<Book> bookList = Util.retrieve();
		for(Book book : bookList )
			if(book.getCategory().equals(category)) internalBookList.add(book);
		return internalBookList;
	}
	
	/** Filter by language 
	  A client can use a REST call to retrieve a list of books by providing language 
	 * @throws IOException */
	@RequestMapping(value = "visma/books/filterByLanguage/{language}", method = RequestMethod.GET)
	public List<Book> getBookByLanguage(@PathVariable("language") String language) throws IOException {
		List<Book> internalBookList = new ArrayList<Book>();
		List<Book> bookList = Util.retrieve();
		for(Book book : bookList )
			if(book.getLanguage().equals(language)) internalBookList.add(book);
		return internalBookList;
	}
	
	
	/**  Filter by ISBN
	 *  A client can use a REST call to retrieve a list of books by providing isbn 
	 * @throws IOException */
	@RequestMapping(value = "visma/books/filterByIsbn/{isbn}", method = RequestMethod.GET)
	public List<Book> getBookByIsbn(@PathVariable("isbn") String isbn) throws IOException {
		List<Book> internalBookList = new ArrayList<Book>();
		String validatedIsbn = Util.validateIsbn(isbn);
		if (validatedIsbn.equals(null)) return null;
		
		List<Book> bookList = Util.retrieve();
		for(Book book : bookList)
			if(book.getIsbn().equals(isbn)) internalBookList.add(book);
		return internalBookList;
	}
	
		
	/** Filter by name
	 A client can use a REST call to retrieve a list of books by providing name 
	 * @throws IOException */
	@RequestMapping(value = "visma/books/filterByName/{name}", method = RequestMethod.GET)
	public List<Book> getBookByName(@PathVariable("name") String name) throws IOException {
		List<Book> internalBookList = new ArrayList<Book>();
		List<Book> bookList = Util.retrieve();
		for(Book book : bookList )
			if(book.getName().equals(name)) internalBookList.add(book);
		return internalBookList;
	}
	
	
	/** Filter taken or available books.
	  A client can use a REST call to retrieve a list of  Available books  
	 * @throws IOException */
	@RequestMapping(value = "visma/books/getAvailable/", method = RequestMethod.GET)
	public List<Book> getAvailableBooks() throws IOException {
		List<Book> internalBookList = new ArrayList<Book>();
		List<Book> bookList = Util.retrieve();
		for(Book book : bookList)
			if(book.getAvailable()) internalBookList.add(book);
		return internalBookList;
	}
	
	
	

}
