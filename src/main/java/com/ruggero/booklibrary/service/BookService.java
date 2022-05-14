package com.ruggero.booklibrary.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.ruggero.booklibrary.dto.TakeBookDTO;
import com.ruggero.booklibrary.entities.Book;
import com.ruggero.booklibrary.entities.BookLibraryUser;
import com.ruggero.booklibrary.repository.BookRepository;
import com.ruggero.booklibrary.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

   @Autowired
   private BookRepository bookRepository;

   public Book saveBook(Book book) {
      return  bookRepository.save(book);
   }

   public Book getBookById(String guid) {
      Book returnValue;
      try {
         UUID.fromString(guid);
         returnValue = bookRepository.getBookById(guid).get();
      } catch (IllegalArgumentException  exception) {
         //handle the case where string is not valid UUID
         returnValue = null;
      }
      return returnValue;
   }

   public List<Book> getAllBooks() {
      return bookRepository.getAllBooks();
   }

   public void deleteBookById(String guid) {
      bookRepository.deleteBookById(guid);

   }

   public List<Book> filterBook(String title, String author,String category, String language, String isbn, boolean taken) {
      return bookRepository.filterBook(title, author, category, language,isbn, taken);
   }

   public void deleteAllBooks() {
      bookRepository.deleteAllBooks();
   }

   public void takeBook(TakeBookDTO dto) {
      BookLibraryUser user = UserRepository.getInstance().getBookLibraryUserList().stream().filter(user1 ->
         user1.getUserId().equals(dto.getUserId()))
            .collect(Collectors.collectingAndThen(Collectors.toList(), list -> {
               if (list.size() != 1) {
                  throw new RuntimeException();
               } else {
                  return list.get(0);
               }
                  }));

      Book book = bookRepository.getBookById(dto.getGuid()).get();

      if (dto.getPeriodOfDays() < 60 && user.getBorrowedBooks().size() < 3  &&  book.getAvailable()) {
         book.setUser(user);
         book.setBookedPeriodDays(dto.getPeriodOfDays());
         book.setAvailable(false);
         user.getBorrowedBooks().add(book);
      }

      bookRepository.deleteBookById(book.getGuid());
      bookRepository.save(book);

   }

   public Book returnBook(String guid) {
      Book book = getBookById(guid);
      BookLibraryUser user = book.getUser();
      book.setAvailable(true);
      book.setUser(null);
      book.setBookedPeriodDays(0);
      bookRepository.deleteBookById(guid);

      user.getBorrowedBooks().remove(book);

      return  bookRepository.save(book);

   }
}
