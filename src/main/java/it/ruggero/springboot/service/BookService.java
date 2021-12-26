package it.ruggero.springboot.service;

import it.ruggero.springboot.data.entity.Book;
import it.ruggero.springboot.data.entity.User;
import it.ruggero.springboot.repository.BookRepository;
import it.ruggero.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    UserRepository userRepository;

    public List<Book> getAllBooks() {
        return  bookRepository.findAll();
    }

    public Book getBookByGuid(String guid) {
        return bookRepository.findById(guid).orElse(null);
    }

    public List<Book> getBookByTitle(String title) { return  bookRepository.findByTitle( title);}

    public List<Book> getBookByAuthor(String author ) {
        return bookRepository.findByAuthor(author);
    }

    public List<Book> getBookByCategory(String category ) {
        return bookRepository.findByCategory(category) ;
    }

    public List<Book> getBookByLanguage(String language) { return bookRepository.findByLanguage(language);}

    public List<Book> getBookByRuggero(String title, String author ) { return bookRepository.findRuggero(title, author);}


    public Book addBook(Book book) {
        return bookRepository.save(book);

    }

    public Book deleteBook(String guid) {
        Book book = bookRepository.findById(guid).orElse(null);
        if(book != null){
            bookRepository.delete(book);
        }
        return book;
    }


    public Book takeBook(String bookId, int userId, int days) {

        User user = userRepository.getById(userId);
        if(user.getTakenBooks().size() > 2 || days > 20 ) {
            return null;
        }
        Book book =bookRepository.getById(bookId);
        book.setUser(user);
        book.setAvailable(false);
        book.setBookedPeriodDays(days);
        bookRepository.save(book);
        user.getTakenBooks().add(book);
        userRepository.save(user);
        return  book;
    }

}
