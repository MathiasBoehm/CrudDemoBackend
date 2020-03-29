package de.struktuhr.crudbackend.repo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import de.struktuhr.crudbackend.entity.Post;

@SpringBootTest
public class PostRepoIT {

    @Autowired
    private PostRepo postRepo;

    @Test
    public void checkSimpleQBE() {
        Post post = new Post();
        PageRequest page = PageRequest.of(0, 10, Sort.by("created").descending());
        postRepo.findAll(Example.of(post), page).forEach(System.out::println);;
    }

    @Test
    public void checkQBELike() {
        Post post = new Post();
        post.setTitle("T");
        post.setAuthor("Peter Pan");

        PageRequest page = PageRequest.of(0, 100, Sort.by("created").descending());
        ExampleMatcher matcher = ExampleMatcher.matchingAll()
            .withMatcher("title", ExampleMatcher.GenericPropertyMatchers.startsWith().ignoreCase());

        postRepo.findAll(Example.of(post, matcher), page).forEach(System.out::println);;
    }
}