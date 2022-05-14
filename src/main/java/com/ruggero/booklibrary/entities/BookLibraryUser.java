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

   @Override
   public boolean equals(Object o) {
      if (this == o) {
         return true;
      }
      if (o == null || getClass() != o.getClass()) {
         return false;
      }

      BookLibraryUser user = (BookLibraryUser) o;

      if (userId != null ? !userId.equals(user.userId) : user.userId != null) {
         return false;
      }
      return borrowedBooks != null ? borrowedBooks.equals(user.borrowedBooks) : user.borrowedBooks == null;
   }

   @Override
   public int hashCode() {
      int result = userId != null ? userId.hashCode() : 0;
      result = 31 * result + (borrowedBooks != null ? borrowedBooks.hashCode() : 0);
      return result;
   }
}
