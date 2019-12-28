package de.struktuhr.crudbackend.repo;

import de.struktuhr.crudbackend.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepo extends JpaRepository<Post, Long> {


}