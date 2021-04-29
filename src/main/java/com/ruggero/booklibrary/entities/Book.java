package com.ruggero.booklibrary.entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import  com.ruggero.booklibrary.service.Util;
import java.util.UUID;

import javax.persistence.Entity;

import javax.persistence.Id;

@Entity
public class Book {
	static private int count = 0;

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

	
	 public Book(){
		 guid = null;
	 }
	 

	public Book(String name, String author, String category, String language, String publicationDate, String isbn) {
		
		//setId();
		//this.id=count++;
		setGuid();
		setName(name);
		setAuthor(author);
		setCategory(category);
		setLanguage(language);
		setPublicationDate(publicationDate);
		setIsbn(isbn);
		
	}
	

	
	

	public int getId() {
		return id;
	}

	public void setId() {
		//this.id=count++;
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		setId();
		setGuid();
		setAvailable(true);
		setBookedPeriodDays(-1);
		setBorrowerName("");
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
		/*LocalDate intDate;
		DateTimeFormatter f;
		try {
		f = DateTimeFormatter.ofPattern("dd.MM.yyyy");
		intDate = LocalDate.parse(publicationDateString, f);
		} catch(IllegalArgumentException e) {
			System.out.println("Wrong date!!!");
			this.publicationDate = null;
			return;
		} 
		 if (intDate.compareTo(LocalDate.now()) > 0) {
			System.out.println("Publication date next than current date!");
			this.publicationDate = null;
			return;
			
		}
			
		
		this.publicationDate = intDate.format(f);*/
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		
		this.isbn = Util.validateIsbn(isbn);
		
		
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid() {
		this.guid = UUID.randomUUID().toString();
	}

	public boolean getAvailable() {
		return this.available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public int getBookedPeriodDays() {
		return bookedPeriodDays;
	}

	public void setBookedPeriodDays(int bookedPeriodDays) {
		if (bookedPeriodDays >= -1 && bookedPeriodDays < 61)
			this.bookedPeriodDays = bookedPeriodDays;
		else
			throw new IllegalArgumentException();
	}

	public String getBorrowerName() {
		return borrowerName;
	}

	public void setBorrowerName(String borrowerName) {
		this.borrowerName = borrowerName;
		
		}
	

	@Override
	public String toString() {
		return "Book [id=" + id + ", name=" + name + ", author=" + author + ", category=" + category + ", language="
				+ language + ", publicationDate=" + publicationDate + ", ISBN=" + isbn + ", guid=" + guid + ", status="
				+ available + ", bookedPeriod=" + bookedPeriodDays + ", borrowerName=" + borrowerName + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((guid == null) ? 0 : guid.hashCode());
		result = prime * result + ((isbn == null) ? 0 : isbn.hashCode()); 
		result = prime * result + ((language == null) ? 0 : language.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((publicationDate == null) ? 0 : publicationDate.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
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
		if (guid == null) {
			if (other.guid != null) {
				return false;
			}
		} else if (!guid.equals(other.guid)) {
			
			return false;
		}
		
		if (isbn == null) {
			if(other.isbn != null) {
			return false;
			}
		} else if (!isbn.equals(other.isbn)) {
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
