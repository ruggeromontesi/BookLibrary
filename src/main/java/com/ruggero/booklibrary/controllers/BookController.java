package com.ruggero.booklibrary.controllers;

import java.util.List;

import com.ruggero.booklibrary.entities.Book;
import com.ruggero.booklibrary.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Book REST endpoint")
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
   @Operation(summary = "Returns a book", description = "Takes guid returns a single book")
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

   /**
    * Rest API endpoint to filter books.
    *
    * @return Book
    */
   @GetMapping("/books/filter")
   //@RequestParam(value = "name", defaultValue = "World"
   public List<Book> filterBook(@RequestParam(value = "name", defaultValue = "all") String name, @RequestParam(value = "author",
         defaultValue = "all") String title, @RequestParam(value = "language", defaultValue = "all") String language) {
      return bookService.filterBook(name, title, language);
   }


}
