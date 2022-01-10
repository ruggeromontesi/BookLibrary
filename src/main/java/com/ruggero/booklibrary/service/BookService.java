package com.ruggero.booklibrary.service;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.imageio.IIOException;

import com.ruggero.booklibrary.entities.Book;
import com.ruggero.booklibrary.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

   @Autowired
   private BookRepository bookRepository;

   public Book saveBook(Book book ) {
      Book returnValue;
      if (Util.validateInputBook(book)) {
         returnValue = bookRepository.save(book);
      } else {
         returnValue = null;
      }
      return  returnValue;
   }

   public Book getBookById(String guid) {
      Book returnValue;
      try {
         UUID.fromString(guid);
         returnValue = bookRepository.getBookByGuid(guid);
         returnValue = bookRepository.getBookById(guid).get();
      } catch (IllegalArgumentException | IOException exception) {
         //handle the case where string is not valid UUID
         returnValue = null;
      }
      return returnValue;
   }

   public List<Book> getAllBooks() {
      return bookRepository.getAllBooks();
   }

   public void deleteBookById(String guid){
      bookRepository.deleteBookById(guid);

   }




}
