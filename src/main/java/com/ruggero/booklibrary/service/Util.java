package com.ruggero.booklibrary.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.time.LocalDate;
import java.time.format.*;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruggero.booklibrary.entities.Book;
import com.ruggero.booklibrary.exceptions.DuplicatedGUIDException;

public class Util {
	
	private  static String filePath = ".\\Booklist.json";
	public static String getFilePath() {
		return filePath;
	}
	
	
	/******************************/
	public static void initializeArchiveFile() {
		List<Book> bookList = new ArrayList<Book>();
		File file = new File (getFilePath());
		try {
			file.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(file.exists())
			System.out.println("A new one was  created!");
		
		
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		try {
			objectMapper.writeValue(file, bookList);
		} catch (JsonGenerationException exc) {
			// TODO Auto-generated catch block
			exc.printStackTrace();
		} catch (JsonMappingException exc) {
			// TODO Auto-generated catch block
			exc.printStackTrace();
		} catch (IOException exc) {
			// TODO Auto-generated catch block
			exc.printStackTrace();
		}
	}
	
	/******************************/
	
	
	 public static List<Book> retrieve() throws IOException{
      List<Book> bookList = new ArrayList<>();
      ObjectMapper mapper = new ObjectMapper();
      String strBook ="";
      while(strBook == "") {
          try{
    	      FileReader f = new FileReader(filePath);
              int c;
              while( (c= f.read()) != -1) strBook = strBook + (char)c;
              f.close();
          
          } catch (FileNotFoundException e) {
    	       System.out.println("Archive file not found! A new archive file will be created");
    	       initializeArchiveFile();
    	   
    	  
          } catch(IOException e ) {
    	      System.out.println("An IO Error occurred while reading file at " + (new File(filePath)).getAbsolutePath());
    	      throw e;
          }
      }   
      
      try {
    	  
		bookList = mapper.readValue(strBook, mapper.getTypeFactory().constructCollectionType(List.class, Book.class));
	} catch (JsonMappingException e) {
		System.out.println("JsonMappingException!");
		initializeArchiveFile();
		e.printStackTrace();
		//throw e;
	} catch (JsonProcessingException e) {
		System.out.println("JsonProcessingException!");
		e.printStackTrace();
		throw e;
	}

      
      return bookList;
	 }
	 
	 /*******************/
		public static void store( List<Book> bookList )throws IOException {

			ObjectMapper objectMapper = new ObjectMapper();
			
			
				objectMapper.writeValue(new File(Util.getFilePath() ), bookList);

			
			
			
		}
		
		
		/****validate input book
		 * @throws IOException ***/
		
		public static boolean validateInputBook(Book book) throws IOException {
			
			if( book.getName()==(null)||book.getAuthor()==(null)||book.getCategory()==(null)
					||book.getLanguage()==(null)||book.getPublicationDate().equals("")
					||book.getIsbn().equals("")) return false;
			
			
			for(Book bookInt : retrieve() )
				if(bookInt.looseEquals(book)) {
					System.out.println("Warning: The two books are having smae title, author,category, language and publication date!");
					return false;
				}
			
			
			return(true);
		}
		
		
		
		
		
		public static boolean canBorrow(String borrowerName) throws IOException {
			List<Book> internalBookList = new ArrayList<Book>();
			List<Book> bookList = Util.retrieve();
			for(Book book : bookList ) 
				if(book.getBorrowerName().equals(borrowerName)) internalBookList.add(book);
			if (internalBookList.size()>=3 ) return false;
			else return true;
			
		}
		

		
		
		public static Book getBook(String guid) throws IOException {
	        List<Book> internalBookList = new ArrayList<Book>();
	        List<Book> bookList = Util.retrieve(); 
	        for(Book book : bookList)
	            if (book.getGuid().equals(guid))
	                internalBookList.add(book);
	        if (internalBookList.size() >1 ) throw new DuplicatedGUIDException();
	        if ( internalBookList.size() == 1 ) return internalBookList.get(0);
	        else  if(internalBookList.isEmpty()){
	            System.out.println("No such GUID present!");
	            return new Book();
	        }     
	        return new Book();
		}
		
		
		
		
		
		
		public static String generateRandomString() {
		    byte[] array = new byte[7]; // length is bounded by 7
		    new Random().nextBytes(array);
		    return new String(array, Charset.forName("UTF-8"));
             
		    
		}
		
		
		/*used in JUNIT test to generate random ISBN*/
	    public static String generateRandomIsbn(){
	        String randomIsbn = "";
	        for(int i =0; i< 13 ; i++)
	            randomIsbn = randomIsbn + (int) (10*Math.random());
	            
	        return randomIsbn;
	    }
		
	    /*used in JUNIT test to generate random  LocalDate*/
	    public static String generateRandomDate( ){
	    	LocalDate endExclusive = LocalDate.now();
	    	LocalDate startInclusive = LocalDate.of(1800,01,01);
	        long startEpochDay = startInclusive.toEpochDay();
	        long endEpochDay = endExclusive.toEpochDay();
	        long randomDay = ThreadLocalRandom
	          .current()
	          .nextLong(startEpochDay, endEpochDay);
            String output = LocalDate.ofEpochDay(randomDay).toString();
	        return validateStringToDate(output);
	    	 
	    }
		
		
		public static int generateRandomInteger() {
	        
	      
	        return (int)(Math.random() * 40);
    
		}
		
		
		public static int generateRandomIntegerCapped( int cap) {
	        
		      
	        return (int)(Math.random() * cap -1 );
    
		}
		
		/*used to generate the indexes of the 4 books to borrow*/
		public static int[] indexGenerator(int max){
			   int[] out = new int[4];
			   boolean ok = false;
			   for( int i=0 ; i<out.length; i++ ){
			       int tmp = generateRandomIntegerCapped(max);
			       while(!ok && (i>0)){
			           for(int j=0 ; j<i; j++ ){
			                if(tmp == out[j]){
			                    tmp = generateRandomIntegerCapped(max);
			                    ok = false;
			                } else  ok = true;
			          
			          }
			      }
			      out[i]= tmp;
			   }
			  return out;

			}
		
		
		
		
		
		
		/*validate entered data*/
	     public static String validateStringToDate(String inputString ){
	    	 LocalDate date = null;
	         DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd-MMMM-yyyy");
	         //DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
	         List<DateTimeFormatter> list = new ArrayList<>();
	         list.add(outputFormatter);
	         list.add(DateTimeFormatter.ofPattern("dd/M/yyyy"));
	         list.add(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	         list.add(DateTimeFormatter.ofPattern("dd/MMM/yyyy"));
	         list.add(DateTimeFormatter.ofPattern("dd/MMMM/yyyy"));
	         list.add(DateTimeFormatter.ofPattern("dd.M.yyyy"));
	         list.add(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
	         list.add(DateTimeFormatter.ofPattern("dd.MMM.yyyy"));
	         list.add(DateTimeFormatter.ofPattern("dd.MMMM.yyyy"));
	         list.add(DateTimeFormatter.ofPattern("dd M yyyy"));
	         list.add(DateTimeFormatter.ofPattern("dd MM yyyy"));
	         list.add(DateTimeFormatter.ofPattern("dd MMM yyyy"));
	         list.add(DateTimeFormatter.ofPattern("dd MMMM yyyy"));
	         list.add(DateTimeFormatter.ofPattern("dd   M   yyyy"));
	         list.add(DateTimeFormatter.ofPattern("dd   MM   yyyy"));
	         list.add(DateTimeFormatter.ofPattern("dd   MMM   yyyy"));
	         list.add(DateTimeFormatter.ofPattern("dd   MMMM   yyyy"));
	         list.add(DateTimeFormatter.ofPattern("yyyy.M.dd"));
	         list.add(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
	         list.add(DateTimeFormatter.ofPattern("yyyy.MMM.dd"));
	         list.add(DateTimeFormatter.ofPattern("yyyy.MMMM.dd"));
	         list.add(DateTimeFormatter.ofPattern("yyyy-M-dd"));
	         list.add(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	         list.add(DateTimeFormatter.ofPattern("yyyy-MMM-dd"));
	         list.add(DateTimeFormatter.ofPattern("yyyy-MMMM-dd"));
     
	         for(DateTimeFormatter format : list){
	             try{
	                 date = LocalDate.parse(inputString, format);
	                 //System.out.println("valid format found!");
	                  break;
	             }catch(DateTimeParseException e){
	            	 
	             }
	         }
	         if(date!= null) {
	        	 String  tmp = date.format(outputFormatter).toString();
	        	 
	        	 return tmp ;
	         }
	             else {
	            	 String  tmp="";
	            	 
	            	 return  tmp;
	             }
	    }
		
	     /*validate entered data*/
	     public static boolean compareDatesAsString(String date1AsString, String date2AsString) {
	    	 DateTimeFormatter internalFormatter = DateTimeFormatter.ofPattern("dd  MMMM  yyyy");
	    	 LocalDate date1 = LocalDate.parse(date1AsString, internalFormatter);
	    	 LocalDate date2 = LocalDate.parse(date2AsString, internalFormatter);
	    	 if(date1.isEqual(date2)) return true;
	    	 else return false;
	     }
	     
	     /*validate ISBN*/
	     public static String validateIsbn(String str) {
	         if ( str.length() != 13 ) return "";
	 	     else {
	             for(int i = 0; i< 13; i++){
	                 if( str.charAt(i) < 48 | str.charAt(i) > 57) return "";
	             }
	 	    		 
	 	 }
	         return str;
	     }
	     
	     
	
		
		/*ID gen*/
		
		public static int  idGen() {

              String directoryName = ".\\idgen";
              String fileName = directoryName.concat("\\Count.txt");
              File file  = new File(String.valueOf(fileName));
              File directory = new File(String.valueOf(directoryName));
			 if(!directory.exists()){

	             directory.mkdir();
	            if(!file.exists()){
	                file.getParentFile().mkdir();
	                try {
						file.createNewFile();
						ObjectMapper objectMapper = new ObjectMapper();
						objectMapper.writeValue(file, 0);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            }
	}
			
			
			return 0;
		}
	
	
	
	

}
