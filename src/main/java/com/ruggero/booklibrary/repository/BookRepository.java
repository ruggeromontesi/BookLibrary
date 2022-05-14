
package com.ruggero.booklibrary.repository;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.ruggero.booklibrary.entities.Book;
import org.springframework.stereotype.Repository;

@Repository
public class BookRepository {
   private static final String FILE_NAME = "BookList.json";

   private static final String DIRECTORY_NAME = ".\\repository";

   private static final String filePath = DIRECTORY_NAME + "\\" + FILE_NAME;

   static {
      initialize();
   }

   public Collection<Book> getAllBooks() {
      return retrieve();
   }

   public Optional<Book> getBookById(String guid) {
      List<Book> retrievedBooks = new ArrayList<>();
      retrievedBooks = retrieve().stream().filter(b -> b.getGuid().equals(guid)).collect(Collectors.toList());
      if (retrievedBooks.size() > 1) {
         throw new RuntimeException("Two books with same guid!");
      } else {
         return (retrievedBooks.size() == 1) ? Optional.of(retrievedBooks.get(0)) : Optional.empty();
      }
   }

   public Book save(Book book) {
      Book returnValue;
      Set<Book> bookList = retrieve();
      bookList.add(book);
      store(bookList);
      returnValue =  book;
      return returnValue;

   }

   public void deleteBookById(String guid) {
      if (getBookById(guid).isPresent()) {
         Set<Book> retrievedBooks = retrieve();
         Book bookToBeRemoved =
               retrieve().stream().filter(b -> b.getGuid().equals(guid)).collect(Collectors.toList()).get(0);
         retrievedBooks.remove(bookToBeRemoved);
         store(retrievedBooks);
      }
   }

   public List<Book> filterBook(String title, String author, String category, String language, String isbn, boolean available) {
      return retrieve().stream().filter(book ->
               (title.equals("all") ^ book.getTitle().contains(title))
                     && (author.equals("all") ^ book.getAuthor().equals(author))
                     && (category.equals("all") ^ book.getCategory().equals(category))
                     && (language.equals("all") ^ book.getLanguage().equals(language))
                     && (isbn.equals("all") ^ book.getIsbn().equals(isbn))
                     && (book.getAvailable() == available)
      ).collect(Collectors.toList());
   }

   private static void initialize() {
      File file = new File(filePath);
      File directory = new File(DIRECTORY_NAME);
      if (!directory.exists()) {
         System.out.println("Directory does not exist");
         directory.mkdir();
      }
      if (!file.exists()) {
         System.out.println("File does not exist");

         try {
            file.createNewFile();
         } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println("Error! not possible to generate file");
            e.printStackTrace();
         }
      }
   }

   private Set<Book> retrieve() {
      Set<Book> resultValue = new TreeSet<>(Comparator.comparingInt(Book::getId));
      File datafile = new File(filePath);
      Scanner scanner;
      String value = "";
      try {
         scanner = new Scanner(datafile);
         while (scanner.hasNextLine()) {
            value += scanner.nextLine();
         }

      } catch (IOException e) {
         System.out.println("Problems while reading file!");
      }

      if (!value.equals("")) {
         try {
            ObjectMapper objectMapper =  new ObjectMapper();
            resultValue = objectMapper.readValue(value, objectMapper.getTypeFactory().constructCollectionType(Set.class,
                  Book.class));
         } catch (IOException e) {
            System.out.println("Problem while parsing the retrieved String!");
            e.printStackTrace();
         }
      }
      return  resultValue;
   }

   private void store(Set<Book> bookList) {
      File datafile = new File(filePath);
      ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
      try {
         FileWriter fw = new FileWriter(datafile.getAbsoluteFile());
         BufferedWriter bw = new BufferedWriter(fw);
         String value = objectWriter.writeValueAsString(bookList);

         bw.write(value);
         //new Gson().toJson(mobilePhone)
         bw.close();
      } catch (IOException e) {
         System.out.println("Problem while writing to file!");
      }

   }

   public void deleteAllBooks() {
      Set<Book> bookList = retrieve();
      bookList.clear();
      store(bookList);
   }
}
