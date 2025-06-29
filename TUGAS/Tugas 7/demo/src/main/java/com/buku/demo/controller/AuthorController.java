package com.buku.demo.controller;

import com.buku.demo.model.Author;
import com.buku.demo.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {
    @Autowired
    private AuthorRepository authorRepository;

    @GetMapping("/all")
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    @GetMapping("/by-id/{id}")
    public Author getAuthorById(@PathVariable Long id) {
        return authorRepository.findById(id).orElse(null);
    }

    @PostMapping("/save")
    public Author saveAuthor(@RequestBody Author author) {
        return authorRepository.save(author);
    }

    @PutMapping("/update/{id}")
    public Author updateAuthor(@PathVariable Long id, @RequestBody Author author) {
        author.setId(id);
        return authorRepository.save(author);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteAuthor(@PathVariable Long id) {
        authorRepository.deleteById(id);
    }
}