package itrum.task2.services;

import itrum.task2.exception.NotFoundException;
import itrum.task2.model.Author;
import itrum.task2.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorService {
    private AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository){
        this.authorRepository = authorRepository;
    }

    public Author getAuthorById(Long id){
        return authorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Author with id=" + id + " was not found"));
    }
    public void save(Author author){
        authorRepository.save(author);
    }
}
