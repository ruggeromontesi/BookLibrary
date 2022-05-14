package com.ruggero.booklibrary.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class BookLibraryUser {

   private String userId;

   private List<Book> borrowedBooks = new ArrayList<>();

   public BookLibraryUser() {
   }

   public String getUserId() {
      return userId;
   }

   public BookLibraryUser setUserId(String userId) {
      this.userId = userId;
      return this;
   }

   public List<Book> getBorrowedBooks() {
      return borrowedBooks;
   }



   @Override
   public String toString() {
      return new StringJoiner(", ", BookLibraryUser.class.getSimpleName() + "[", "]")
            .add("userId='" + userId + "'")
            .add("borrowedBooks=" + borrowedBooks)
            .toString();
   }
}
