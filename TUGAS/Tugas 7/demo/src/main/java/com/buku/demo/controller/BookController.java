package com.buku.demo.controller;

import com.buku.demo.model.Book;
import com.buku.demo.repository.AuthorRepository;
import com.buku.demo.repository.BookRepository;
import com.buku.demo.repository.CategoryRepository;
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
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookRepository bookRepository;
    
    @Autowired
    private AuthorRepository authorRepository;
    
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/all")
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @GetMapping("/by-id/{id}")
    public Book getBookById(@PathVariable Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    @PostMapping("/save")
    public Book saveBook(@RequestBody Book book) {
        // Ensure author and category exist
        if (book.getAuthor() != null && book.getAuthor().getId() != null) {
            book.setAuthor(authorRepository.findById(book.getAuthor().getId()).orElse(null));
        }
        if (book.getCategory() != null && book.getCategory().getId() != null) {
            book.setCategory(categoryRepository.findById(book.getCategory().getId()).orElse(null));
        }
        return bookRepository.save(book);
    }

    @PutMapping("/update/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book book) {
        book.setId(id);
        // Ensure author and category exist
        if (book.getAuthor() != null && book.getAuthor().getId() != null) {
            book.setAuthor(authorRepository.findById(book.getAuthor().getId()).orElse(null));
        }
        if (book.getCategory() != null && book.getCategory().getId() != null) {
            book.setCategory(categoryRepository.findById(book.getCategory().getId()).orElse(null));
        }
        return bookRepository.save(book);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookRepository.deleteById(id);
    }
}