package it.ruggero.springboot.controller;

import it.ruggero.springboot.data.entity.Book;
import it.ruggero.springboot.data.entity.User;
import it.ruggero.springboot.service.BookService;
import it.ruggero.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UtilityController {
    @Autowired
    UserService userService;

    @Autowired
    BookService bookService;

    @PostMapping("/ruggero/initialize")
    public void initialize() {
        userService.add(new User("ruggero", "montesi"));
        bookService.addBook(new Book("OCP Oracle Certified Professional Java SE 11 Developer Complete Study Guide: Exam 1Z0-815, Exam 1Z0-816, and Exam 1Z0-817",
                "Boyarsky, Jeanne; Selikoff, Scott",
                "COMPUTER/Certification Guides",
                "english",
                "23.01.2020",
                "9781119619130"));
        bookService.addBook(new Book("OCP Oracle Certified Professional Java SE 11 Programmer II Study Guide: Exam 1Z0-816 and Exam 1Z0-817",
                "Boyarsky, Jeanne; Selikoff, Scott",
                "COMPUTER/Certification Guides",
                "english",
                "23.07.2020",
                "9781119617624"));
        bookService.addBook(new Book("OCP Oracle Certified Professional Java SE 11 Developer Practice Tests",
                "Boyarsky, Jeanne; Selikoff, Scott",
                "COMPUTER/ProgramminglanguageS/Java",
                "english",
                "23.03.2021",
                "9781119696131"));
        bookService.addBook(new Book("Head First Java",
                "Kathy Sierra; Bert Bates",
                "COMPUTER/Programminglanguages/Java",
                "english",
                "23.03.2005",
                "9780596009205"));
        bookService.addBook(new Book("OCP: Oracle Certified Professional Java SE 8 Programmer II Study Guide",
                "Boyarsky, Jeanne; Selikoff, Scott",
                "COMPUTER/Programming Languages/Java",
                "english",
                "23.01.2015",
                "9781119067900"));
        bookService.addBook(new Book("OCA / OCP Java SE 8 Programmer Practice Tests",
                "Boyarsky, Jeanne; Selikoff, Scott",
                "COMPUTER/Certification Guides",
                "english",
                "23.03.2017",
                "9781119363392"));
        bookService.addBook(new Book("OCA Java SE 8 Programmer I Exam Guide (Exams 1Z0-808)",
                "Boyarsky, Jeanne; Selikoff, Scott",
                "COMPUTER/Certification Guides",
                "english",
                "23.03.2017",
                "9781119363392"));
        bookService.addBook(new Book("Java Projects: Learn the fundamentals of Java 11 programming by building industry grade practical projects",
                "Verhas, Peter",
                "not specified",
                "english",
                "05.05.2018",
                "9781789131895"));
        bookService.addBook(new Book("OCA Java SE 8 Programmer II 1Z0-809",
                "EXAM BOOST",
                "not specified",
                "english",
                "05.05.2020",
                "9798649312769"));
        bookService.addBook(new Book("OCP Oracle Certified Professional Java SE 11 Programmer I Study Guide: Exam 1Z0-815",
                "Boyarsky, Jeanne; Selikoff, Scott",
                "COMPUTERS/Certification Guides",
                "english",
                "23.01.2020",
                "9781119584704"));
        bookService.addBook(new Book("Java: A Beginner's Guide, Eighth Edition",
                "Herbert Schildt",
                "Programming/Java",
                "english",
                "23.04.2018",
                "9781260440218"));
        bookService.addBook(new Book("OCA: Oracle Certified Associate Java SE 8 Programmer I Study Guide",
                "Boyarsky, Jeanne; Selikoff, Scott",
                "COMPUTERS/Certifications Guides",
                "english",
                "15.01.2014",
                "9781118957400"));
        //13
        bookService.addBook(new Book("OCA Java SE 8 Programmer I Certification Guide",
                "Gupta, Mala",
                "JAVA CERTIFICATION",
                "english",
                "15.01.2017",
                "9781617293252"));
        //14
        bookService.addBook(new Book("OCAJP Associate Java 8 Programmer Certification Fundamentals: 1z0-808",
                "Hanumanth Deshmukh",
                "JAVA CERTIFICATION",
                "english",
                "05.06.2020",
                "9781720162032"));
        //15
        bookService.addBook(new Book("Nailing 1Z0-808: Practical Guide to Oracle Java SE8 Programmer I Certification",
                "Igor Soudakevitch",
                "JAVA CERTIFICATION",
                "english",
                "05.06.2017",
                "9781548193980"));
        //16
        bookService.addBook(new Book("Java Se 11 Programmer-1 -1z0-815 Practice Test (Topic-Wise): Hundreds of Questions to assess your 1Z0-815 exam preparation arranged by Exam Objectives",
                "Udayan Khattry",
                "JAVA 11 CERTIFICATION",
                "english",
                "01.09.2020",
                "9781691579051"));
        //17
        bookService.addBook(new Book("Oracle Certified Professional Java SE 8 Programmer II 1Z0-809 Practice Tests: 500+ Questions to assess your OCP preparation",
                "Udayan Khattry",
                "JAVA 8 CERTIFICATION",
                "english",
                "10.10.2020",
                "9781980649410"));
        //18
        bookService.addBook(new Book("Java: The Complete Reference, Eleventh Edition",
                "Herbert Schildt",
                "Programming/Java",
                "english",
                "23.04.2019",
                "9781260440232"));
        //19
        bookService.addBook(new Book("Non morirò stanotte",
                "Karim Franceschi",
                "Sirian civil war",
                "italian",
                "25.09.2018",
                "9788817101998"));

    }




}
