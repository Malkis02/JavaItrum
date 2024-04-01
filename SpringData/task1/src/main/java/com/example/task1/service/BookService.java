package com.example.task1.service;

import com.example.task1.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public List<Book> getAllBooks() {
        return jdbcTemplate.query("SELECT * FROM books", new BeanPropertyRowMapper<>(Book.class));
    }

    public Book getBookById(Long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM books WHERE id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class));
    }

    public Book save(Book book) {
        jdbcTemplate.update("INSERT INTO books (title, author, publication_year) VALUES (?, ?, ?)",
                book.getTitle(), book.getAuthor(), book.getPublicationYear());
        return book;
    }

    public void deleteBook(Long id) {
        jdbcTemplate.update("DELETE FROM books WHERE id = ?", id);
    }

    public Book updateBook(Long id, Book updatedBook) {
        jdbcTemplate.update("UPDATE books SET title = ?, author = ?, publication_year = ? WHERE id = ?",
                updatedBook.getTitle(), updatedBook.getAuthor(), updatedBook.getPublicationYear(), id);
        return updatedBook;
    }
}
