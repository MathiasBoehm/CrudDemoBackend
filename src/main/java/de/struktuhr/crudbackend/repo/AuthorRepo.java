package de.struktuhr.crudbackend.repo;

import de.struktuhr.crudbackend.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepo extends JpaRepository<Author, Long> {

    
}