package it.ruggero.springboot.data.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "Book")
public class Book {
    @Id
    //@Column(name = "book_id")
    private String guid;

    //@Column(name = "book_title")
    private String title;

    //@Column(name = "book_author")
    private String author;

    //@Column(name = "book_category")
    private String category;

    //@Column(name = "book_language")
    private String language;

    @Column(name = "book_publication_date")
    private String publicationDate;

    //@Column(name = "book_isbn")
    private String isbn;

    //@Column(name = "book_available")
    private boolean available;

    @Column(name = "book_booked_period_days")
    private int bookedPeriodDays;

    @Column(name = "book_borrower_name")
    private String borrowerName;

    @ManyToOne
    @JoinColumn(name = "book_user_id")
    @JsonIgnoreProperties("takenBooks")
    private User user;

    public Book() {
        setGuid();
    }

    public Book(String name, String author, String category) {
        setGuid();
        this.title = name;
        this.author = author;
        this.category = category;
    }

    public Book(String title, String author, String category, String language, String publicationDate, String isbn) {
        setGuid();
        this.title = title;
        this.author = author;
        this.category = category;
        this.language = language;
        this.publicationDate = publicationDate;
        this.isbn = isbn;
        this.available = true;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getGuid() { return guid; }

    public void setGuid() {
        this.guid = UUID.randomUUID().toString();
    }

    public boolean isAvailable() {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
