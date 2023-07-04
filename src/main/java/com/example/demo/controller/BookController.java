package com.example.demo.controller;

import com.example.demo.model.Author;
import com.example.demo.model.Book;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Autowired
    public BookController(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }
    @GetMapping
    public String getAllBooks(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "book-list";
    }

    @GetMapping("/{id}")
    public String getBookById(@PathVariable("id") Long id, Model model) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid book Id: " + id));
        model.addAttribute("book", book);
        return "book-details";
    }

    @GetMapping("/new")
    public String showBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "book-form";
    }

    @PostMapping("/new")
    public String createBook(@ModelAttribute("book") Book book) {
        Author author = book.getAuthor();
        if (author != null) {
            authorRepository.save(author);
            book.setAuthor(author);
            bookRepository.save(book);
        }
        return "redirect:/books";
    }



    @GetMapping("/{id}/edit")
    public String showEditBookForm(@PathVariable("id") Long id, Model model) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid book id: " + id));
        model.addAttribute("book", book);
        model.addAttribute("author", book.getAuthor()); // Add the author to the model
        return "update-book";
    }



    @PostMapping("/{id}/edit")
    public String updateBook(@PathVariable("id") Long id, @RequestParam("title") String title, @RequestParam("authorName") String authorName) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid book id: " + id));

        existingBook.setTitle(title);

        Author author = existingBook.getAuthor();
        if (author != null) {
            author.setName(authorName);
            authorRepository.save(author);
        }

        bookRepository.save(existingBook);

        return "redirect:/books/" + id;
    }




    @PostMapping("/{id}/delete")
    public String deleteBook(@PathVariable("id") Long id) {
        bookRepository.deleteById(id);
        return "redirect:/books";
    }
}
