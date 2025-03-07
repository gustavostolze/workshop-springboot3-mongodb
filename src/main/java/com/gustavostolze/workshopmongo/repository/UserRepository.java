package com.gustavostolze.workshopmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.gustavostolze.workshopmongo.domain.User;

public interface UserRepository extends MongoRepository<User, String> {

}
