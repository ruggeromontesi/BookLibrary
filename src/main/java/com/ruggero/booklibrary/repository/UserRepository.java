package com.ruggero.booklibrary.repository;

import java.util.ArrayList;
import java.util.List;

import com.ruggero.booklibrary.entities.BookLibraryUser;
import org.springframework.stereotype.Repository;

public class UserRepository {
   private static final UserRepository instance = new UserRepository();

   private List<BookLibraryUser> bookLibraryUserList = new ArrayList<>();

   private UserRepository() {

   }

   public static UserRepository getInstance() {
      return  instance;
   }

   public List<BookLibraryUser> getBookLibraryUserList() {
      return bookLibraryUserList;
   }
}
