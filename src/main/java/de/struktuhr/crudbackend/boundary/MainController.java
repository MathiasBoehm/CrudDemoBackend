package de.struktuhr.crudbackend.boundary;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.struktuhr.crudbackend.control.PostService;
import de.struktuhr.crudbackend.entity.Post;

@RestController
@RequestMapping(value = "/public")
public class MainController {


    private final PostService postService;

    public MainController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("recent-posts")
    public List<Post> recentPosts() {
        final List<Post> posts = postService.findRecentPosts();
        return posts;
    }
    
}