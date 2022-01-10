package com.ruggero.booklibrary.entities;

import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.ruggero.booklibrary.service.Util;

@Entity
public class Book {

   private static int count = 0;

   @Id
   private  int id = count++;

   private String name;

   private String author;

   private String category;

   private String language;

   private String publicationDate;

   private String isbn;

   private	String guid;

   private boolean available;

   private int bookedPeriodDays;

   private String borrowerName;

   public Book() {
   }

   public Book(String name, String author, String category, String language, String publicationDate, String isbn) {
      setGuid();
      this.name = name;
      this.author = author;
      this.category = category;
      this.language = language;
      this.publicationDate = publicationDate;
      this.isbn = isbn;
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

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
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
      this.publicationDate = Util.validateStringToDate(publicationDateString);
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

   public String getBorrowerName() {
      return borrowerName;
   }

   public void setBorrowerName(String borrowerName) {
      this.borrowerName = borrowerName;
   }

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
   }

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
      if (!getName().equals(book.getName())) {
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
      return getBorrowerName().equals(book.getBorrowerName());
   }

   @Override
   public int hashCode() {
      int result = getId();
      result = 31 * result + getName().hashCode();
      result = 31 * result + getAuthor().hashCode();
      result = 31 * result + getCategory().hashCode();
      result = 31 * result + getLanguage().hashCode();
      result = 31 * result + getPublicationDate().hashCode();
      result = 31 * result + getIsbn().hashCode();
      result = 31 * result + getGuid().hashCode();
      result = 31 * result + (getAvailable() ? 1 : 0);
      result = 31 * result + getBookedPeriodDays();
      result = 31 * result + getBorrowerName().hashCode();
      return result;
   }

   public boolean looseEquals(Object obj) {
      if (this == obj) {
         return true;
      }
      if (!(obj instanceof Book)) {
         return false;
      }
      Book other = (Book) obj;
      if (author == null) {
         if (other.author != null) {
            return false;
         }
      } else if (!author.equals(other.author)) {
         return false;
      }
      if (category == null) {
         if (other.category != null) {
            return false;
         }
      } else if (!category.equals(other.category)) {
         return false;
      }

      if (isbn != other.isbn) {
         return false;
      }
      if (language == null) {
         if (other.language != null) {
            return false;
         }
      } else if (!language.equals(other.language)) {
         return false;
      }
      if (name == null) {
         if (other.name != null) {
            return false;
         }
      } else if (!name.equals(other.name)) {
         return false;
      }
      if (publicationDate == null) {
         if (other.publicationDate != null) {
            return false;
         }
      } else if ( ! publicationDate.equals(other.publicationDate))  {

         System.out.println("different publication date!");
         return false;
      }
      return true;
   }
}
