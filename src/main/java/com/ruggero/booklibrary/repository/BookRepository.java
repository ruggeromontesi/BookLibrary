
package com.ruggero.booklibrary.repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.ruggero.booklibrary.entities.Book;
import com.ruggero.booklibrary.service.Util;
import org.springframework.stereotype.Repository;

@Repository
public class BookRepository {

   private  static String filePath = ".\\Booklist.json";

   public Book getBookByGuid(String guid) throws IOException {
      return Util.getBook(guid);
   }

   public List<Book> getAllBooks() {
      List<Book> retrievedBooks = new ArrayList<>();
      try {
         retrievedBooks = Util.retrieve();
      } catch (IOException exception) {
         System.out.println("handle the exception");
      }
      return retrievedBooks;
   }

   public Optional<Book> getBookById(String guid) {
      List<Book> retrievedBooks = new ArrayList<>();
      try {
         retrievedBooks = Util.retrieve().stream().filter(b -> b.getGuid().equals(guid)).collect(Collectors.toList());
      } catch (IOException exception) {
         System.out.println("handle the exception");
      }

      if (retrievedBooks.size() > 1) {
         throw new RuntimeException("Two books with same guid!");
      } else {
         return (retrievedBooks.size() == 1)? Optional.of(retrievedBooks.get(0)): Optional.empty();
      }
   }

   public Book save(Book book) {
      Book returnValue;
      try {
         List<Book> bookList = Util.retrieve();
         bookList.add(book);
         Util.store(bookList);
         returnValue =  book;
      } catch(IOException exception ) {
         returnValue = null;
      }
      return returnValue;

   }

   public void deleteBookById(String guid) {

      if (getBookById(guid).isPresent()) {

         try {
            List<Book> retrievedBooks = Util.retrieve();
            Book bookToBeRemoved =
                  Util.retrieve().stream().filter(b -> b.getGuid().equals(guid)).collect(Collectors.toList()).get(0);
            retrievedBooks.remove(bookToBeRemoved);
            Util.store(retrievedBooks);
         } catch (IOException exception) {
            System.out.println("handle the exception");
         }
      }
   }
}
