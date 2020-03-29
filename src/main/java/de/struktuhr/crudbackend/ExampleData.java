package de.struktuhr.crudbackend;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import de.struktuhr.crudbackend.entity.Author;
import de.struktuhr.crudbackend.entity.Post;
import de.struktuhr.crudbackend.repo.AuthorRepo;
import de.struktuhr.crudbackend.repo.PostRepo;

@Component
public class ExampleData {

    private final static Logger logger = LoggerFactory.getLogger(ExampleData.class);

    private final static String[] WOMEN = { "Linda Love", "Jenny Elvers", "Sandra Sunday", "Andrea Fury", "Anna Car" };
    private final static String[] MEN = { "John Doe", "Peter Pan", "Joe Baur", "Gerardo Guliani", "Marc Little" };
    private final static String[] TOPICS = { "Flowers", "Lifestyle", "Tech", "Sports", "Traveling", "Cars" };
    private final static String CONTENT = "<p>Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt</p>";
    private final static String[] DATES = { "2020-01-05", "2019-04-03", "2019-08-04", "2019-12-06", "2020-03-03",
            "2018-10-15" };

    private final AuthorRepo authorRepo;
    private final PostRepo postRepo;
    private final Random random;

    public ExampleData(AuthorRepo authorRepo, PostRepo postRepo) {
        this.authorRepo = authorRepo;
        this.postRepo = postRepo;
        this.random = new Random();
    }

    public void createExampleData() {
        createAuthors();
        createPosts();
    }

    private void createAuthors() {
        Arrays.stream(WOMEN).forEach(name -> createAuthor(name, "women"));
        Arrays.stream(MEN).forEach(name -> createAuthor(name, "men"));
    }

    private void createPosts() {
        authorRepo.findAll().stream().forEach(author -> createPosts(author));
    }

    private void createPosts(Author author) {
        int maxPosts = random.nextInt(20);
        if (maxPosts == 0) {
            maxPosts = 1;
        }
        int i = 0;
        while (i++ <= maxPosts) {
            Post post = new Post();
            post.setAuthor(author.getName());
            post.setAuthorImageUrl(author.getImageUrl());
            post.setContent(CONTENT);
            post.setTitle(randomTitle());
            post.setCreated(randomCreatedDate());
            postRepo.save(post);
        }
        logger.info("Author {} created with {} posts", author.getName(), maxPosts);
    }

    private LocalDate randomCreatedDate() {
        int index = random.nextInt(DATES.length);
        return LocalDate.parse(DATES[index]);
    }

    private String randomTitle() {
        int index = random.nextInt(TOPICS.length);
        return TOPICS[index];
    }

    private void createAuthor(String name, String type) {
        final String imageUrl = String.format("https://randomuser.me/api/portraits/%s/%d.jpg", type, random.nextInt(89) + 10);
        Author author = new Author();
        author.setName(name);
        author.setImageUrl(imageUrl);
        authorRepo.save(author);
    }
}