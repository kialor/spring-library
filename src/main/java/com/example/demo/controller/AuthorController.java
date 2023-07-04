package com.example.demo.controller;

import com.example.demo.model.Author;
import com.example.demo.model.Book;
import com.example.demo.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/authors")
public class AuthorController {
    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @GetMapping
    public String getAllAuthors(Model model) {
        model.addAttribute("authors", authorRepository.findAll());
        return "author-list";
    }

    @GetMapping("/{id}")
    public String getAuthorById(@PathVariable("id") Long id, Model model) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid author id: " + id));
        model.addAttribute("author", author);
        return "author-details";
    }

    @GetMapping("/new")
    public String showAuthorForm(Model model) {
        model.addAttribute("author", new Author());
        return "author-form";
    }

    @PostMapping("/new")
    public String createAuthor(@ModelAttribute("author") Author author) {
        authorRepository.save(author);
        return "redirect:/authors";
    }

    @GetMapping("/{id}/edit")
    public String showEditAuthorForm(@PathVariable("id") Long id, Model model) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid author id: " + id));
        model.addAttribute("author", author);
        return "author-form";
    }

    @PostMapping("/{id}/edit")
    public String updateAuthor(@PathVariable("id") Long id, @ModelAttribute("author") Author updatedAuthor) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid author id: " + id));

        author.setName(updatedAuthor.getName());
        authorRepository.save(author);
        return "redirect:/authors";
    }

    @PostMapping("/{id}/delete")
    public String deleteAuthor(@PathVariable("id") Long id) {
        authorRepository.deleteById(id);
        return "redirect:/authors";
    }

}
