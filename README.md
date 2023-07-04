# spring-library

Git clone the project and run in IDE. Open localhost:8080, the library homepage.
The application provides the following functionalities and how to test them:

Book Management:
Get All Books: Access the /books endpoint to retrieve a list of all books by selecting the View List of Books link.
Get Book by ID: Access the /books/{id} endpoint to retrieve the details of a specific book by selecting the Details link.
On the details page, the book title and author can be updated by editing the text in the textbox and saving. Return to the list
of books to see the updated book information. By selecting the delete button, the book will be removed from the list.
Create New Book: Access the /books/new endpoint to create a new book by submitting a form by selecting the Add Book link.
The form requires a book title and author to be entered before hitting the create button.

Author Management:
To Get All Authors: Access the /authors endpoint to retrieve a list of all authors by selecting the View Authors link.
Get Author by ID: Access the /authors/{id} endpoint to retrieve the details of a specific author by selecting the Details link.
On the details page, the author name can be updated by editing the text in the textbox and saving. Return to the list of authors
to see the updated author information. To delete an author, ensure there is not an associated book with the author, if there is none,
the author can be successfully deleted.
Create New Author: Access the /authors/new endpoint to create a new author by submitting a form.


