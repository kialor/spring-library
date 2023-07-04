package com.example.demo;

import com.example.demo.model.Author;
import com.example.demo.model.Book;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BooksInLibrary implements CommandLineRunner {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;


    @Autowired
    public BooksInLibrary(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }


    @Override
    public void run(String... args) throws Exception {

        Author author1 = new Author("Ludwig Bemelmans");
        Author author2 = new Author("Marcus Pfister");
        authorRepository.save(author1);
        authorRepository.save(author2);

        Book book1 = new Book("Madeline", author1);
        Book book2 = new Book("The Rainbow Fish", author2);
        bookRepository.save(book1);
        bookRepository.save(book2);
    }
}
