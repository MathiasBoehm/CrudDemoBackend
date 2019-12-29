package de.struktuhr.crudbackend.boundary;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.struktuhr.crudbackend.control.AuthorService;
import de.struktuhr.crudbackend.entity.Author;

@RestController
@RequestMapping(value = "/api/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public List<Author> findAll() {
        return authorService.findAll();
    }
}