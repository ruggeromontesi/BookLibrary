package it.ruggero.springboot.controller;

import it.ruggero.springboot.data.entity.Book;
import it.ruggero.springboot.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {
    @Autowired
    BookService bookService;

    @RequestMapping("/ruggero/books")
    List<Book> getAllBooks( ) {
        return bookService.getAllBooks();
    }

    @GetMapping("/ruggero/books/{guid}")
    Book getBookById(@PathVariable("guid") String guid) {
        return bookService.getBookByGuid(guid);
    }

    @GetMapping("/ruggero/books/bytitle/{title}")
    List<Book> getBookByTitle(@PathVariable("title") String title) {
        return bookService.getBookByTitle(title);
    }

    @GetMapping("/ruggero/books/bylanguage/{language}")
    List<Book> getBookByLanguage(@PathVariable("language") String language) {
        return bookService.getBookByLanguage(language);
    }

    @GetMapping("/ruggero/books/bycategory/{category}")
    List<Book> getBookByCategory(@PathVariable("category") String category) { return  bookService.getBookByCategory(category);}



    @GetMapping("/ruggero/books/byruggero/{title}/{author}")
    List<Book> getBookByRuggero(@PathVariable("title") String title, @PathVariable("author") String author) {
        return bookService.getBookByRuggero(title, author);
    }

    @GetMapping("/ruggero/books/{criteria}/{criteriaValue}")
    List<Book> getBookByCriteria(@PathVariable("criteria") String criteria, @PathVariable("criteriaValue") String criteriaValue ) {
        if(criteria.equals("author")) {
            return  bookService.getBookByAuthor(criteriaValue);
        }
        return null;
    }



    @PostMapping("/ruggero/books")
    Book addBook(@RequestBody Book book) {
        return  bookService.addBook(book);
    }

    @DeleteMapping("/ruggero/books/{guid}")
    Book deleteBookById(@PathVariable("guid") String guid) {
        return bookService.deleteBook(guid);

    }

    @PostMapping("/ruggero/books/{bookId}/{userId}/{days}")
    Book addBook(@PathVariable("bookId") String bookId,@PathVariable("userId") int userId,@PathVariable("days") int days ) {

        return  bookService.takeBook(bookId,userId,days);
    }

}
