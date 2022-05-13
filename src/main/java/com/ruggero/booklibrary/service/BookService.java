package com.ruggero.booklibrary.service;

import java.util.List;
import java.util.UUID;

import com.ruggero.booklibrary.entities.Book;
import com.ruggero.booklibrary.repository.BookRepository;
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

   public List<Book> filterBook(String title, String author,String category, String language) {
      return bookRepository.filterBook(title, author, category, language);
   }

   public void deleteAllBooks() {
      bookRepository.deleteAllBooks();
   }
}
