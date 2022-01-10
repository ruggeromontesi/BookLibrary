package com.ruggero.booklibrary.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.ruggero.booklibrary.entities.Book;
import com.ruggero.booklibrary.repository.BookRepository;
import com.ruggero.booklibrary.service.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(MockitoExtension.class)
@WebMvcTest
public class BookRestControllerMvcTest {

   private  static final String BOOKS_URL = "/books";

   @Autowired
   private MockMvc mockMvc;

   @MockBean
   private BookRepository bookRepository;

   @MockBean
   private BookService bookService;

   @Test
   public void testFindAll() throws Exception {
      List<Book> books = createBooks();

      //books.add(book);
      when(bookService.getAllBooks()).thenReturn(books);
      //when(bookRepository.getAllBooks()).thenCallRealMethod();
      ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
      mockMvc.perform(get(BOOKS_URL)).andExpect(status().isOk());
      String expectation = objectWriter.writeValueAsString(books);
      mockMvc.perform(get(BOOKS_URL)).andExpect(status().isOk()).andExpect(content().json(expectation));
   }

   @Test
   public void  testAddBook() throws Exception{
      Book book = new Book();
      book.setName("ruggero");
      //when(bookService.saveBook(any()).thenReturn(book);
      when(bookService.saveBook(any())).thenReturn(book);
      //when(bookRepository.save(book)).thenReturn(book);

      ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
      String expectation = objectWriter.writeValueAsString(book);

      mockMvc.perform(
            post(BOOKS_URL)
                  .contentType(MediaType.APPLICATION_JSON)
                  .content(objectWriter.writeValueAsString(book))
      ).andExpect(status().isOk())
            .andExpect(content().json(expectation));
   }





   private List<Book> createBooks() {
      Book book = new Book();
      book.setName("ruggero");
      Book book1 = new Book();
      book1.setName("montesi");
      return Arrays.asList(book, book1);
   }
}
