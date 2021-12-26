package it.ruggero.springboot.repository;

import it.ruggero.springboot.data.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,String> {


    List<Book> findByTitle(String title);
    List<Book> findByAuthor(String author);
    List<Book> findByCategory(String category);
    List<Book> findByLanguage(String language);


    @Query("SELECT b FROM Book b WHERE b.title = :title AND b.author =:author")
    List<Book>  findRuggero(String title,String author  );





}
