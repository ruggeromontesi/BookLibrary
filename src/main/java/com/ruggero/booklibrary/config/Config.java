package com.ruggero.booklibrary.config;

import java.util.List;

import com.ruggero.booklibrary.entities.BookLibraryUser;
import com.ruggero.booklibrary.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
   @Bean
   public void initializeUserRepository() {
      List<BookLibraryUser> bookLibraryUserList = UserRepository.getInstance().getBookLibraryUserList();
      bookLibraryUserList.add(new BookLibraryUser().setUserId("ruggero"));
      bookLibraryUserList.add(new BookLibraryUser().setUserId("michele"));


   }
}
