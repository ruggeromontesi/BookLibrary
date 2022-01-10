package com.ruggero.booklibrary.controllers;

import java.util.List;

import com.ruggero.booklibrary.entities.Book;
import com.ruggero.booklibrary.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

   @Autowired
   private BookService bookService;

   /** Rest API endpoint to  add a Book.
    *
    * @return Book */
   @PostMapping("/books")
   public Book addBook(@RequestBody Book book) {
      return bookService.saveBook(book);
   }

   /** Rest API endpoint to  get a book by GUID.
    * @param guid
    * @return Book */
   @GetMapping("/books/{guid}")
   public Book getBookById(@PathVariable("guid") String guid) {
      return bookService.getBookById(guid);
   }

   /** Rest API endpoint to  get all books.
        * @return List<Book> */
   @GetMapping("/books")
   public List<Book> getAllBooks() {
      return bookService.getAllBooks();
   }

   /** Rest API endpoint to  get a book by GUID.
    * @param guid
    * @return Book */
   @DeleteMapping("/books/{guid}")
   public Book deleteBookById(@PathVariable("guid") String guid) {
      bookService.deleteBookById(guid);
      return null;
   }


}
