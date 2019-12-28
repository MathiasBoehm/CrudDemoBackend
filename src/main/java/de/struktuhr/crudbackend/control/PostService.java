package de.struktuhr.crudbackend.control;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import de.struktuhr.crudbackend.entity.Post;
import de.struktuhr.crudbackend.exception.InvalidDataException;
import de.struktuhr.crudbackend.exception.NotFoundException;
import de.struktuhr.crudbackend.repo.PostRepo;

@Service
public class PostService {


    private final PostRepo postRepo;

    public PostService(PostRepo postRepo) {
        this.postRepo = postRepo;
    }

	public List<Post> findAll() {
		return postRepo.findAll();
	}

	public long countAll() {
		long count = postRepo.count();
		return count;
	}

	public List<Post> find(String sortField, String order, Integer page, Integer size) {
		if (page != null && size != null && size > 0) {
			Pageable pageable = buildPageable(sortField, order, page, size);
			Page<Post> pagePosts = postRepo.findAll(pageable);
			return pagePosts.getContent();
		}
		else {
			return postRepo.findAll();
		}
	}

	public Post get(Long id) {
		return internalGetPost(id);
	}

	public Post create(Post post) {
		validate(post);

		post.setCreated(LocalDate.now());
		
		return postRepo.saveAndFlush(post);
	}

	public void delete(Long id) {
		final Optional<Post> found = postRepo.findById(id);
        if (found.isPresent()) {
            postRepo.deleteById(found.get().getId());
        }
	}

	public Post update(Long id, Post post) {
		Post p = internalGetPost(id);

		validate(post);

		p.setAuthor(post.getAuthor());
		p.setAuthorImageUrl(post.getAuthorImageUrl());
		p.setTitle(post.getTitle());
		p.setContent(post.getContent());
		
		return postRepo.saveAndFlush(p);
	}

	private Pageable buildPageable(String sortField, String order, Integer page, Integer size) {
		Sort sort = null;
		
		if (sortField != null) {
			sort = "desc".equalsIgnoreCase(order) ? Sort.by(sortField).descending() : Sort.by(sortField);
		}

		return sort != null ? PageRequest.of(page, size, sort) : PageRequest.of(page, size);
	}

	private void validate(Post post) {
		if (StringUtils.isEmpty(post.getAuthor())) {
			throw new InvalidDataException("author must not be empty");
		}

		if (StringUtils.isEmpty(post.getAuthorImageUrl())) {
			throw new InvalidDataException("authorImageUrl must not be empty");
		}

		if (StringUtils.isEmpty(post.getTitle())) {
			throw new InvalidDataException("title must not be empty");
		}

		if (StringUtils.isEmpty(post.getContent())) {
			throw new InvalidDataException("content must not be empty");
		}
	}
	
	private Post internalGetPost(Long id) {
        return postRepo.findById(id).orElseThrow(() -> new NotFoundException("Cannot find Post with id " + id));
	}
}