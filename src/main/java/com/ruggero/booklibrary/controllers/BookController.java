package com.ruggero.booklibrary.controllers;

import java.util.List;

import com.ruggero.booklibrary.dto.TakeBookDTO;
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

   private final BookService bookService;

   @Autowired
   public BookController(BookService bookService) {
      this.bookService = bookService;
   }

   /** Rest API endpoint to  add a Book.
    * @param book book to be added
    * @return Book */
   @PostMapping("/books")
   public Book addBook(@RequestBody  Book book) {
      return bookService.saveBook(book);
   }

   /** Rest API endpoint to  get a book by GUID.
    * @param guid GUID of book to be retrieved
    * @return Book retrieved book
    * */
   @GetMapping("/books/{guid}")
   @Operation(summary = "Returns a book", description = "Takes guid returns a single book")
   public Book getBookById(@PathVariable("guid") String guid) {
      return bookService.getBookById(guid);
   }

   /** Rest API endpoint to  get all books.
   * @return List<@Book> all books available in the connection
   **/
   @GetMapping("/books")
   public List<Book> getAllBooks() {
       return bookService.getAllBooks();
   }

   /** Rest API endpoint to  get a book by GUID.
    * @param guid guid of the book to be deleted
    * @return Book
    * */
   @DeleteMapping("/books/{guid}")
   public Book deleteBookById(@PathVariable("guid") String guid) {
      bookService.deleteBookById(guid);
      return null;
   }

   /**
    * Rest API endpoint to filter books.
    * @param title all books with field author equal to this parameter are retrieved
    * @param author all books with field author equal to this parameter are retrieved
    * @param category all books with field category equal to this parameter are retrieved
    * @param language all books with field category equal to this parameter are retrieved
    * @param isbn all books with field category equal to this parameter are retrieved
    * @param taken all books with field taken equal to this parameter are retrieved
    * @return Book
    */
   @GetMapping("/books/filter")
   //@RequestParam(value = "name", defaultValue = "World"
   public List<Book> filterBook(@RequestParam(value = "author", defaultValue = "all") String title,
                                @RequestParam(value = "author", defaultValue = "all") String author,
                                @RequestParam(value = "category", defaultValue = "all") String category,
                                @RequestParam(value = "language", defaultValue = "all") String language,
                                @RequestParam(value = "isbn", defaultValue = "all") String isbn,
                                @RequestParam(value = "taken", defaultValue = "false") boolean taken
                                ) {
      return bookService.filterBook(title,author, category, language, isbn, taken);
   }


   /** Rest API endpoint to  get a book by GUID.
    * @return List<@Book>
    * */
   @DeleteMapping("/books")
   public void deleteAllBooks() {
       bookService.deleteAllBooks();
   }

   /** Rest API endpoint to take a book.
    * @param dto
    * @return Book
    * */
   @PostMapping("/books/take")
   public Book takeBook(@RequestBody TakeBookDTO dto) {
      bookService.takeBook(dto);
      return null;
   }

   @PostMapping("/books/return/{guid}")
   public Book returnBook(@PathVariable("guid") String guid) {
      return bookService.returnBook(guid);
   }


}
