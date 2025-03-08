package com.gustavostolze.workshopmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.gustavostolze.workshopmongo.domain.Post;

public interface PostRepository extends MongoRepository<Post, String> {

}
