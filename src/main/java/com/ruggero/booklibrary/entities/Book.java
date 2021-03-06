package com.ruggero.booklibrary.entities;

import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Book {

   private static int count = 0;

   @Id
   private  int id = count++;

   private String title;

   private String author;

   private String category;

   private String language;

   private String publicationDate;

   private String isbn;

   private	String guid;

   private boolean available;

   private int bookedPeriodDays;

   @JsonIgnoreProperties("borrowedBooks")
   @ManyToOne
   private BookLibraryUser user;

   public Book() {
      setGuid();
      this.available = true;

   }

   public Book(String name, String author, String category, String language, String publicationDate, String isbn) {
      setGuid();
      this.title = name;
      this.author = author;
      this.category = category;
      this.language = language;
      this.publicationDate = publicationDate;
      this.isbn = isbn;
      this.available = true;

   }

   public static int getCount() {
      return count;
   }

   public static void setCount(int count) {
      Book.count = count;
   }

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getTitle() {
      return title;
   }

   public void setTitle(String name) {
      this.title = name;
   }

   public String getAuthor() {
      return author;
   }

   public void setAuthor(String author) {
      this.author = author;
   }

   public String getCategory() {
      return category;
   }

   public void setCategory(String category) {
      this.category = category;
   }

   public String getLanguage() {
      return language;
   }

   public void setLanguage(String language) {
      this.language = language;
   }

   public String getPublicationDate() {
      return publicationDate;
   }

   public void setPublicationDate(String publicationDateString) {
      this.publicationDate = publicationDateString;
   }

   public String getIsbn() {
      return isbn;
   }

   public void setIsbn(String isbn) {
      this.isbn = isbn;
   }

   public String getGuid() {
      return guid;
   }

   private void setGuid() {
      this.guid = UUID.randomUUID().toString();
   }

   public boolean getAvailable() {
      return available;
   }

   public void setAvailable(boolean available) {
      this.available = available;
   }

   public int getBookedPeriodDays() {
      return bookedPeriodDays;
   }

   public void setBookedPeriodDays(int bookedPeriodDays) {
      this.bookedPeriodDays = bookedPeriodDays;
   }

   public BookLibraryUser getUser() {
      return user;
   }

   public void setUser(BookLibraryUser borrowerName) {
      this.user = borrowerName;
   }

   /*
   @Override
   public String toString() {
      return "Book{"
            + "id=" + id + '\''
            + ", name='" + name + '\''
            + ", author='" + author + '\''
            + ", category='" + category + '\''
            + ", language='" + language + '\''
            + ", publicationDate='" + publicationDate + '\''
            + ", isbn='" + isbn + '\''
            + ", guid='" + guid + '\''
            + ", available=" + available
            + ", bookedPeriodDays=" + bookedPeriodDays
            + ", borrowerName='" + borrowerName + '\''
            + '}';
   }*/

   @Override
   public boolean equals(Object o) {
      if (this == o) {
         return true;
      }
      if (!(o instanceof Book)) {
         return false;
      }

      Book book = (Book) o;

      if (getId() != book.getId()) {
         return false;
      }
      if (getAvailable() != book.getAvailable()) {
         return false;
      }
      if (getBookedPeriodDays() != book.getBookedPeriodDays()) {
         return false;
      }
      if (!getTitle().equals(book.getTitle())) {
         return false;
      }
      if (!getAuthor().equals(book.getAuthor())) {
         return false;
      }
      if (!getCategory().equals(book.getCategory())) {
         return false;
      }
      if (!getLanguage().equals(book.getLanguage())) {
         return false;
      }
      if (!getPublicationDate().equals(book.getPublicationDate())) {
         return false;
      }
      if (!getIsbn().equals(book.getIsbn())) {
         return false;
      }
      if (!getGuid().equals(book.getGuid())) {
         return false;
      }
      //return getUser().equals(book.getUser());
      return getUser() == null ? (book.getUser() == null ? true :false) : getUser().equals(book.getUser());
   }

   @Override
   public int hashCode() {
      int result = id;
      result = 31 * result + title.hashCode();
      result = 31 * result + author.hashCode();
      result = 31 * result + category.hashCode();
      result = 31 * result + language.hashCode();
      result = 31 * result + publicationDate.hashCode();
      result = 31 * result + isbn.hashCode();
      result = 31 * result + guid.hashCode();
      result = 31 * result + (available ? 1 : 0);
      result = 31 * result + bookedPeriodDays;
      //result = 31 * result + (user != null ? user.hashCode() : 0);
      return result;
   }

}
