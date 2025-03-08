package com.gustavostolze.workshopmongo.services;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gustavostolze.workshopmongo.domain.Post;
import com.gustavostolze.workshopmongo.repository.PostRepository;
import com.gustavostolze.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository postRepository;

	public Post findById(String id) {
		Optional<Post> obj = postRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found!"));
	}

	public List<Post> findByTitle(String text) {
		return postRepository.searchTitle(text);
	}
	
	public List<Post> fullSearch(String text, Instant dateMin, Instant dateMax) {
		return postRepository.fullSearch(text, dateMin, dateMax);
	}
}
