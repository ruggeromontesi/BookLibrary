Book Library Task /*****************************************************************************************************************************************************/ Rest API endpoint to add a new book. URI for the API is http://localhost:8080/visma/saveBook/

Example of JSON Body { "name":"some title", "author":"some author", "category":"some category", "language":"some language", "publicationDate" : "23.04.2021", "isbn":"1234567890123"

} NOTE: The fields name, author, category, language and isbn must be there. If one of them is not there the book will not be saved. The field publicationDate can be entered in various format such as dd/MMMM/yyyy,dd.MMMM.yyyy, dd MMM yyyy. Internally the date is saved as string, in the format dd-MMMM-yyyy. Date has to be valid and in one of many supported formats, otherwise the book is not saved.

ISBN is defined here as a 13 digit string. (See wikipedia https://en.wikipedia.org/wiki/International_Standard_Book_Number ) ISBN has to be valid, i.e. 13 digits, otherwise the book is not saved. /*****************************************************************************************************************************************************/

All the book data are stored in a JSON file : .\Booklist.json No database is used. When bootstraping the application,no file is present. At the occurrence of the first query, the application tries to read the json file. If this is not present the file is created and an empty list is saved there. Upon each Book creation action the list of Books is saved on the file.(The list is retrieved from file and an ArrayList is created, the book is added and then the list is written on the file) Similar sequence occurs upon any "write" action like delete, take or release a book

/*****************************************************************************************************************************************************/ Rest API endpoint to take a book from the library.

Endpoint should specify who is taking the book and for what period the book is taken.

Taking the book longer than two months should not be allowed. Taking more than 3 books should not be allowed.

http://localhost:8080//takeBook/{guid}/{borrowerName}/{bookedPeriodDays}

Example

http://localhost:8080/visma/takeBook/0f93d286-8bbc-481b-8748-daded563f677/ruggero montesi/45/ NOTE: In case of invalid guid the endpoint returns an empty book and no book is taken.

In the following cases no book is taken the endpoint reurns the book with status unchanged 1)invalid period of time ( number minor than 0 or bigger than 60) 2)an user took already three books.

/*****************************************************************************************************************************************************/

Rest API endpoint to get a book by GUID. http://localhost:8080/visma/getBookByGuid/5db60e09-2240-4b2b-b75b-1ea6b61de73e/ In case of invalid guid the endpoint returns an empty book and no book is taken.

/*****************************************************************************************************************************************************/

Rest API endpoint to list all the books. http://localhost:8080/visma/books/

/*****************************************************************************************************************************************************/ Filter by author "http://localhost:8080/visma/books/filterByAuthor/{author}" Example http://localhost:8080/visma/books/filterByAuthor/Schildt

Returns the list of books with the given author. If no book with the given author is present an empty list is returned.

/*****************************************************************************************************************************************************/ Filter by category "http://localhost:8080/visma/books/filterByCategory/{category}" Example http://localhost:8080/visma/books/filterByCategory/programming language Returns the list of books with the given category. If no book with the given category is present an empty list is returned.

/*****************************************************************************************************************************************************/ Filter by language "http://localhost:8080/visma/books/filterByLanguage/{language}" http://localhost:8080/visma/books/filterByLanguage/italian

Returns the list of books with the given language. If no book with the given language is present an empty list is returned.

/*****************************************************************************************************************************************************/ Filter by ISBN "http://localhost:8080/visma/books/filterByIsbn/{isbn}" http://localhost:8080/visma/books/filterByIsbn/12001

Returns the list of books with the given ISBN. If no book with the given ISBN is present an empty list is returned. If incorrect ISBN is given an empty list is returned. /*****************************************************************************************************************************************************/ Filter by name

"http://localhost:8080/visma/books/filterByName/{name}" http://localhost:8080/visma/books/filterByName/dummy

Returns the list of books with the given name. If no book with the given name is present an empty list is returned.

/*****************************************************************************************************************************************************/

Filter taken or available books. "http://localhost:8080/visma/books/getAvailable"

/*****************************************************************************************************************************************************/

Rest API endpoint to delete a book. "http://localhost:8080/visma/deleteBook/{guid} http://localhost:8080/visma/deleteBook/a82e6c95-b4ed-4c3c-beb5-860a36412176/

/*****************************************************************************************************************************************************/ Unit tests The two following classes are provided : BookLibraryTest and MyJsonTests. BookLibraryTest tests with JUnit the required functionalities. Book objects are created with randomly generated fields.

MyJsonTests tests serialization.
