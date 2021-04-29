package com.ruggero.booklibrary.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.json.*;
import com.ruggero.booklibrary.service.*;
import org.springframework.boot.test.json.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.ruggero.booklibrary.entities.Book;


@JsonTest
public class MyJsonTests {
	 @Autowired
	 private JacksonTester<Book> json;
	 
	 @Test
	 void testSerialize() throws Exception {
     String name = Util.generateRandomString();
     String author = Util.generateRandomString();
     String category = Util.generateRandomString();
     String language = Util.generateRandomString();
     String isbn = Util.generateRandomIsbn();
     String publicationDate = Util.generateRandomDate();
     Book details = new  Book( name, author, category, language, publicationDate,isbn );
	 // Assert against a `.json` file in the same package as the test
	 //assertThat(this.json.write(details)).isEqualToJson("expected.json");
	 // Or use JSON path based assertions
     assertThat(this.json.write(details)).hasJsonPathStringValue("@.name");
	 assertThat(this.json.write(details)).extractingJsonPathStringValue("@.name").isEqualTo(name);
	 assertThat(this.json.write(details)).hasJsonPathStringValue("@.author");
	 assertThat(this.json.write(details)).extractingJsonPathStringValue("@.author").isEqualTo(author);
     assertThat(this.json.write(details)).hasJsonPathStringValue("@.category");
	 assertThat(this.json.write(details)).extractingJsonPathStringValue("@.category").isEqualTo(category);
     assertThat(this.json.write(details)).hasJsonPathStringValue("@.language");
	 assertThat(this.json.write(details)).extractingJsonPathStringValue("@.language").isEqualTo(language);
     assertThat(this.json.write(details)).hasJsonPathStringValue("@.publicationDate");
	 assertThat(this.json.write(details)).extractingJsonPathStringValue("@.publicationDate").isEqualTo(Util.validateStringToDate(publicationDate));
	 assertThat(this.json.write(details)).hasJsonPathStringValue("@.isbn");
     assertThat(this.json.write(details)).extractingJsonPathStringValue("@.isbn").isEqualTo(isbn);
     assertThat(this.json.write(details)).hasJsonPathStringValue("@.guid");
     assertThat(this.json.write(details)).extractingJsonPathStringValue("@.guid").isNotNull();
     assertThat(this.json.write(details)).hasJsonPathBooleanValue("@.available");
     assertThat(this.json.write(details)).extractingJsonPathBooleanValue("@.available").isEqualTo(true);
     assertThat(this.json.write(details)).hasJsonPathNumberValue("@.bookedPeriodDays");
     assertThat(this.json.write(details)).extractingJsonPathNumberValue("@.bookedPeriodDays").isEqualTo(-1);
     assertThat(this.json.write(details)).hasJsonPathStringValue("@.borrowerName");
	 assertThat(this.json.write(details)).extractingJsonPathStringValue("@.borrowerName").isEqualTo("");
     
	 
	 }

}
