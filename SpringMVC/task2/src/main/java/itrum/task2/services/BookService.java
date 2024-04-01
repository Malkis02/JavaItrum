package itrum.task2.services;

import itrum.task2.exception.NotFoundException;
import itrum.task2.model.Author;
import itrum.task2.model.Book;
import itrum.task2.repositories.AuthorRepository;
import itrum.task2.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;


@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BookService {

    private final BookRepository bookRepository;


    public Page<Book> getAllBooks(Pageable pageable){
        return bookRepository.findAll(pageable);
    }

    public Book getBookById(Long bookId){
        return bookRepository.findById(bookId).orElseThrow(() -> new NotFoundException("Book with id=" + bookId + " was not found"));
    }

    public Book save(Book book){
        if (book.getAuthors().isEmpty()) {
            throw new IllegalArgumentException("can't save a book without its authors");
        }
        return bookRepository.save(book);
    }

    public void deleteBook(Long bookId){
        if(!bookRepository.existsById(bookId)){
            throw new NotFoundException("Book with id=" + bookId + " was not found");
        }
        bookRepository.deleteById(bookId);
    }

    public Book updateBook(Book book,Long bookId){
        if(!bookRepository.existsById(bookId)){
            throw new NotFoundException("Book with id=" + bookId + " was not found");
        }
        return new Book(book.getTitle(), book.getYear(), book.getAuthors());
    }
}
