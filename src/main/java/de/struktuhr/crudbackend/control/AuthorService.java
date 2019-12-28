package de.struktuhr.crudbackend.control;

import java.util.List;

import org.springframework.stereotype.Service;

import de.struktuhr.crudbackend.entity.Author;
import de.struktuhr.crudbackend.repo.AuthorRepo;

@Service
public class AuthorService {

    private final AuthorRepo authorRepo;

    public AuthorService(AuthorRepo authorRepo) {
        this.authorRepo = authorRepo;
    }

    public List<Author> findAll() {
        return authorRepo.findAll();
    }
}