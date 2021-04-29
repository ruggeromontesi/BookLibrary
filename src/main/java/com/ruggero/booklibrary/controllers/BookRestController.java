package com.ruggero.booklibrary.controllers;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.ruggero.booklibrary.entities.Book;
import com.ruggero.booklibrary.service.Util;
import com.ruggero.booklibrary.exceptions.*;



@RestController
public class BookRestController {
	
	/**	 Rest API endpoint to add a new book */
	@RequestMapping(value = "visma/saveBook/", method = RequestMethod.POST)
	public Book createBook(@RequestBody Book  book ) throws IOException {
		
	    if (Util.validateInputBook(book)) {	
		    List<Book> bookList = Util.retrieve(); 
		    bookList.add(book);
		    Util.store(bookList);
		    return  book;
	    } else return null;
	}
	
	/**Rest API endpoint to delete a book.* @throws IOException */
	@RequestMapping(value = "visma/deleteBook/{guid}", method = RequestMethod.DELETE)
	public Book deleteBook(@PathVariable("guid") String guid) throws IOException {
		
		List<Book> internalBookList = new ArrayList<Book>();
		List<Book> bookList = Util.retrieve();
        for(Book book : bookList)
            if (book.getGuid().equals(guid))
        internalBookList.add(book);
        
        
         
		switch (internalBookList.size()) {
		case 0:
			System.out.println("No such GUID present!");
            return null;
                        
		case 1:
           Book book = internalBookList.get(0);
       	   if( bookList.remove(book)) System.out.println("deleted!"); 
           else  System.out.println("Not deleted!");
           Util.store(bookList);;
           return book;
			
		default:
			
			throw new DuplicatedGUIDException();
		}
	}		

	
	
}
