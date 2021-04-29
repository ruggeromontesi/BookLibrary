package com.ruggero.booklibrary.test;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.ruggero.booklibrary.controllers.BookRestController;
import com.ruggero.booklibrary.controllers.Manage;
import com.ruggero.booklibrary.entities.Book;
import com.ruggero.booklibrary.service.Util;
import com.ruggero.booklibrary.controllers.Filter;

@SpringBootTest
public class BookLibraryTest {
	@Autowired
	Manage manage;
	
	@Autowired
	BookRestController restController;
	
	@Autowired
	Filter filter;
	
	
	
	@Test
	void testTake() throws IOException{
	     String name = Util.generateRandomString();
	     String author = Util.generateRandomString();
	     String category = Util.generateRandomString();
	     String language = Util.generateRandomString();
	     String isbn = Util.generateRandomIsbn();
	     String publicationDate = Util.generateRandomDate();
	     Book book = new  Book( name, author, category, language, publicationDate,isbn );
		
		
		try {
			restController.createBook(book);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Book bookint = manage.takeBook(book.getGuid(), "ruggero montesi", 40);
		assertThat(bookint.getAvailable()).isEqualTo(false);
		restController.deleteBook(book.getGuid());
	}
	
	
	@Test
	void testTakingLongerThanAllowed() throws IOException{
	     String name = Util.generateRandomString();
	     String author = Util.generateRandomString();
	     String category = Util.generateRandomString();
	     String language = Util.generateRandomString();
	     String isbn = Util.generateRandomIsbn();
	     String publicationDate = Util.generateRandomDate();
	     Book book = new  Book( name, author, category, language, publicationDate,isbn );
		
		restController.createBook(book);
		Book bookint = manage.takeBook(book.getGuid(), "ruggero montesi", 65);
		assertThat(bookint.getAvailable()).isEqualTo(true);
		restController.deleteBook(book.getGuid());
		
	}
	
	
	@Test
	void testTakeMoreThanThreeBooks() throws IOException{

		
		Book book1 = new  Book( "test title1", "test author1", "test category1", "test language1", "23.04.2021",Util.generateRandomIsbn() );
		Book book2 = new  Book( "test title2", "test author2", "test category2", "test language2", "23.04.2021",Util.generateRandomIsbn() );
		Book book3 = new  Book( "test title3", "test author3", "test category3", "test language3", "23.04.2021",Util.generateRandomIsbn());
		Book book4 = new  Book( "test title4", "test author4", "test category4", "test language4", "23.04.2021",Util.generateRandomIsbn() );
		Book book5 = new  Book( "test title5", "test author5", "test category5", "test language5", "23.04.2021",Util.generateRandomIsbn() );
		restController.createBook(book1);
		restController.createBook(book2);
		restController.createBook(book3);
		restController.createBook(book4);
		restController.createBook(book5);
		manage.takeBook(book1.getGuid(), "dummy user", 15);
		manage.takeBook(book2.getGuid(), "dummy user", 15);
		manage.takeBook(book3.getGuid(), "dummy user", 15);
		
		Book bookint = manage.takeBook(book4.getGuid(), "dummy user", 15);
		assertThat(bookint.getAvailable()).isEqualTo(true);
		restController.deleteBook(book1.getGuid());
		restController.deleteBook(book2.getGuid());
		restController.deleteBook(book3.getGuid());
		restController.deleteBook(book4.getGuid());
		restController.deleteBook(book5.getGuid());
	}
	
	@Test
	/*beta , might not in all cases work!*/
	void testTakeMoreThanThreeBooksRandom() throws IOException{
		String dummyUser = Util.generateRandomString();
		List<Book> list = new ArrayList<>();
		int sizeList ;
		
		do {
			sizeList = Util.generateRandomInteger();
			
		} while( sizeList < 10);
		
		int[] indexList = Util.indexGenerator(sizeList -1 ); 
		
		for(int i=0 ; i< sizeList; i++)
			list.add( new Book( Util.generateRandomString(), Util.generateRandomString(), Util.generateRandomString(), Util.generateRandomString(),Util.generateRandomDate(), Util.generateRandomIsbn()  ) );
		
		for(Book book : list ) restController.createBook(book);
		
        for(int j=0; j <indexList.length-1; j++ ) {
        	Book tmpBook = manage.takeBook(list.get(indexList[j]).getGuid(), dummyUser, Util.generateRandomIntegerCapped(30));
        	assertThat(tmpBook.getAvailable()).isEqualTo(false);
        }

		
		Book bookint = manage.takeBook(list.get(indexList[indexList.length-1]).getGuid(), dummyUser, Util.generateRandomIntegerCapped(40));
		System.out.println(bookint.getAvailable());
		assertThat(bookint.getAvailable()).isEqualTo(true);
		for(Book book : list) restController.deleteBook(book.getGuid());
	
	}
	
	
	
	 
	@Test
	void testGetABookByGuid()throws IOException {
		Book book0 = new  Book( "title testGetABookByGuid", "author testGetABookByGuid", "category testGetABookByGuid", "language testGetABookByGuid", "23.04.2021", Util.generateRandomIsbn() );
		
		restController.createBook(book0);

		Book testBook = Manage.getBookByGuid(book0.getGuid());
		assertThat(testBook.equals(book0)).isEqualTo(true);
		restController.deleteBook(book0.getGuid());
		
	}
	
	
	
	
	@Test
	void testFilterAuthor() throws IOException {
		String  str = Util.generateRandomString();
		Book book1 = new  Book( "test title1", str, "test category1", "test language1", "23.04.2021",Util.generateRandomIsbn() );
		Book book2 = new  Book( "test title2", str, "test category2", "test language2", "23.04.2021",Util.generateRandomIsbn() );
		restController.createBook(book1);
		restController.createBook(book2);
		assertThat(filter.getBookByAuthor(str).size()).isEqualTo(2);
		restController.deleteBook(book1.getGuid());
		restController.deleteBook(book2.getGuid());
		
	}
	
	
	
	
	@Test
	void testFilterCategory() throws IOException {
		String  str = Util.generateRandomString();
		Book book1 = new  Book( "test title1", "test author1", str, "test language1", "23.04.2021",Util.generateRandomIsbn() );
		Book book2 = new  Book( "test title2", "test author2", str, "test language2", "23.04.2021",Util.generateRandomIsbn() );
		restController.createBook(book1);
		restController.createBook(book2);
		assertThat(filter.getBookByCategory(str).size()).isEqualTo(2);
		restController.deleteBook(book1.getGuid());
		restController.deleteBook(book2.getGuid());
		
	}
	
	
	@Test
	void testFilterLanguage() throws IOException {
		String  str = Util.generateRandomString();
		Book book1 = new  Book( "test title1", "test author1", "test category1", str, "23.04.2021",Util.generateRandomIsbn() );
		Book book2 = new  Book( "test title2", "test author2", "test category2", str, "23.04.2021",Util.generateRandomIsbn());
		restController.createBook(book1);
		restController.createBook(book2);
		assertThat(filter.getBookByLanguage(str).size()).isEqualTo(2);
		restController.deleteBook(book1.getGuid());
		restController.deleteBook(book2.getGuid());
		
	}
	
	
	
	@Test
	void testFilterISBN() throws IOException {
		String  isbn = Util.generateRandomIsbn();
		Book book1 = new  Book( "test title1", "test author1", "test category1", "test language1", "23.04.2021",isbn );
		Book book2 = new  Book( "test title2", "test author2", "test category2", "test language2", "23.04.2021",isbn );
		restController.createBook(book1);
		restController.createBook(book2);
		assertThat(filter.getBookByIsbn(isbn).size()).isEqualTo(2);
		restController.deleteBook(book1.getGuid());
		restController.deleteBook(book2.getGuid());
		
	}
	
	
	@Test
	void testFilterName() throws IOException {
		String  str = Util.generateRandomString();
		Book book1 = new  Book( str, "test author1", "test category1", "test language1", "23.04.2021",Util.generateRandomIsbn() );
		Book book2 = new  Book( str, "test author2", "test category2", "test language2", "23.04.2021",Util.generateRandomIsbn() );
		restController.createBook(book1);
		restController.createBook(book2);
		assertThat(filter.getBookByName(str).size()).isEqualTo(2);
		restController.deleteBook(book1.getGuid());
		restController.deleteBook(book2.getGuid());
		
	}
	
	
	@Test
	void testFilterAvailable() throws IOException {
		//all books from file are retrived
		List<Book> list = filter.retrieveAllBooks();
		
		List<String> indexOfNotAvailableBook = new ArrayList<>();
		List<String> borrowerNameList = new ArrayList<>();
		List<Integer> bookedPeriodDaysList   = new ArrayList<>();
		 
		
		for(Book book : list) {
			if(!book.getAvailable()) {
				indexOfNotAvailableBook.add( book.getGuid()   );
				bookedPeriodDaysList.add(book.getBookedPeriodDays());
				borrowerNameList.add(book.getBorrowerName());
				
				manage.releaseBook(book.getGuid());
			}
			
		}
		
				
		
		
		Book book1 = new  Book( "test title1", "test author1", "test category1", "test language1", "23.04.2021",Util.generateRandomIsbn() );
		Book book2 = new  Book( "test title2", "test author2", "test category2", "test language2", "23.04.2021",Util.generateRandomIsbn() );
		restController.createBook(book1);
		restController.createBook(book2);
		manage.takeBook(book1.getGuid(), "dummy user", 15);
		manage.takeBook(book2.getGuid(), "dummy user", 15);
		assertThat(filter.getAvailableBooks().size()).isEqualTo(list.size());
		 
		for(int i=0;  i< indexOfNotAvailableBook.size() && i< bookedPeriodDaysList.size() 
				&& i< borrowerNameList.size(); i++   ){
			  manage.takeBook(indexOfNotAvailableBook.get(i), borrowerNameList.get(i),  bookedPeriodDaysList.get(i));
		}
		
		restController.deleteBook(book1.getGuid());
		restController.deleteBook(book2.getGuid());
		
		
		
	}
	
	
	@Test
	void testDelete() throws IOException {
		
		Book book1 = new  Book( "test title1", "test author1", "test category1", "test language1", "23.04.2021",Util.generateRandomIsbn() );

		restController.createBook(book1);
		assertThat(filter.retrieveAllBooks().contains(book1)).isEqualTo(true);
		restController.deleteBook(book1.getGuid());
		assertThat(filter.retrieveAllBooks().contains(book1)).isEqualTo(false);
		
	}
	
	
	
	
	
	

}
