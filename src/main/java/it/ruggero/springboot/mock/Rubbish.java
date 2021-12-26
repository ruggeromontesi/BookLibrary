package it.ruggero.springboot.mock;

import it.ruggero.springboot.data.entity.Book;

import java.util.ArrayList;
import java.util.List;

public class Rubbish {
    private static List<Book> bookList = new ArrayList<>();
    static {
        createMockList();
    }


    public static List<Book> getBookList() {
        return bookList;
    }

    private static void createMockList( ) {
        Book book = new Book("OCP Oracle Certified Professional Java SE 11 Developer Complete Study Guide: Exam 1Z0-815, Exam 1Z0-816, and Exam 1Z0-817", "Boyarsky, Jeanne; Selikoff, Scott","COMPUTER/Certification Guides" );
        bookList.add(book);

    }
}
