package de.struktuhr.crudbackend.boundary;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import de.struktuhr.crudbackend.control.PostService;
import de.struktuhr.crudbackend.entity.Post;

@RestController
@RequestMapping(value = "/api/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("count")
    public long count(@RequestParam(name = "title", required = false) String title,
                      @RequestParam(name = "author", required = false) String author) {
        final long count = postService.count(title, author);
        return count;
    }
    
    @GetMapping
    public ResponseEntity<List<Post>> find(@RequestParam(name = "title", required = false) String title,
        @RequestParam(name = "author", required = false) String author,
        @RequestParam(name = "_sort", required = false) String sortField, 
        @RequestParam(name = "_order", required = false) String order, 
        @RequestParam(name = "_page", required = false) Integer page, 
        @RequestParam(name = "_size", required = false) Integer size) {

        final List<Post> posts = postService.find(title, author, sortField, order, page, size);
        final long count = postService.count(title, author);

        return ResponseEntity.ok().header("X-Total-Count", String.valueOf(count)).body(posts);
    }
    
    @GetMapping("{id}")
    public Post getPost(@PathVariable("id") Long id) {
        return postService.get(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Post create(@RequestBody Post post) {
        return postService.create(post);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Long id) {
        postService.delete(id);
    }

    @PutMapping("{id}")
    public Post update(@PathVariable("id") Long id, @RequestBody Post post) {
        return postService.update(id, post);
    }
}