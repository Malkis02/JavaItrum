package itrum.task2.controllers;

import itrum.task2.exception.NotFoundException;
import itrum.task2.model.Author;
import itrum.task2.model.Book;
import itrum.task2.services.AuthorService;
import itrum.task2.services.BookService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/books")
@Validated
public class Controller {
    private BookService bookService;

    @Autowired
    public Controller(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping()
    public ResponseEntity<Page<Book>> getAllBooks(@RequestParam(defaultValue = "10") @Min(1) Integer size,
                                                  @RequestParam(defaultValue = "0") @Min(0) Integer from) {

        return ResponseEntity.status(HttpStatus.OK).body(bookService.getAllBooks(PageRequest.of(from / size, size)));
    }


    @GetMapping("/{bookId}")
    public ResponseEntity<?> getBookById(@PathVariable Long bookId) {
        Book book = bookService.getBookById(bookId);
            return ResponseEntity.status(HttpStatus.OK).body(book);
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody @Valid Book book) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.save(book));
    }


    @PutMapping("/{bookId}")
    public ResponseEntity<?> updateBook(@PathVariable Long bookId, @RequestBody @Valid Book book) {
        Book updateBook = bookService.updateBook(book,bookId);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(updateBook);
    }


    @DeleteMapping("/{bookId}")
    public ResponseEntity<?> deleteBook(@PathVariable Long bookId) {
        bookService.deleteBook(bookId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleProductNotFoundException(NotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}
