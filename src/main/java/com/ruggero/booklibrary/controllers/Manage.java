package com.ruggero.booklibrary.controllers;

import java.io.IOException;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.ruggero.booklibrary.entities.Book;

import com.ruggero.booklibrary.service.Util;



@RestController
public class Manage {
	

	/** Rest API endpoint to get a book by GUID. 
	 * @throws IOException */
	@RequestMapping(value = "visma/getBookByGuid/{guid}", method = RequestMethod.GET)
	public static Book getBookByGuid(@PathVariable("guid") String guid) throws IOException {
		return Util.getBook(guid);
	}
	
		
	
	
	/**  Rest API endpoint to take a book from the library. Endpoint should specify who is
    taking the book and for what period the book is taken. Taking the book longer than two months should not be allowed. 
   Taking more than 3 books should not be allowed.
	 * @throws IOException 
	*/
		@RequestMapping(value = "visma/takeBook/{guid}/{borrowerName}/{bookedPeriodDays}", method = RequestMethod.PUT)
	       public Book takeBook(@PathVariable("guid") String guid, @PathVariable("borrowerName") String borrowerName,
			@PathVariable("bookedPeriodDays") int bookedPeriodDays) throws IOException {
			List<Book> bookList = Util.retrieve(); 
			
             Book book = Util.getBook(guid);
             
             
             int index = bookList.indexOf(book);
             
             
             boolean canBorrow = Util.canBorrow(borrowerName);
              if(!canBorrow | bookedPeriodDays >60 | bookedPeriodDays < 0 | !book.getAvailable() )
           	   return book;
              else {
            	  bookList.get(index).setAvailable(false);
            	  bookList.get(index).setBookedPeriodDays(bookedPeriodDays);
            	  bookList.get(index).setBorrowerName(borrowerName);
           	  

           	    Util.store(bookList);
           	   return bookList.get(index);
              }
             

           
		}
		
		
		
		

		
		
		@RequestMapping(value = "visma/releaseBook/{guid}", method = RequestMethod.PUT)
		public Book releaseBook(@PathVariable("guid") String guid) throws IOException {
			Book book = Util.getBook(guid);
			List<Book> bookList = Util.retrieve();
			int index = bookList.indexOf(book); 
			if ( book.getAvailable() ) return null;
			else {
          	       bookList.get(index).setAvailable(true);
          	       bookList.get(index).setBookedPeriodDays(-1);
          	       bookList.get(index).setBorrowerName("");
				   

		     	  //
          	     Util.store(bookList);;
		     	 return bookList.get(index);
			}

			
		}
		
		
	
	
	
	

}
