package it.ruggero.springboot.data.entity;

import javax.persistence.*;
import java.util.Set;
import java.util.TreeSet;

@Entity
@Table(name ="Usr")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;
    private String name;

    private String surname;

    @OneToMany(fetch = FetchType.EAGER , mappedBy = "user" , cascade = CascadeType.ALL)
    //JsonIgnoreProperties({ "user","publicationDate","category","language", "isbn"})
    private Set<Book> takenBooks = new TreeSet<>();

    public User ( ) {}

    public User(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Set<Book> getTakenBooks() {
        return takenBooks;
    }
}
