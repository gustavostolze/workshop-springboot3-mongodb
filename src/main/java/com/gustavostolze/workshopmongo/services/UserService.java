package com.gustavostolze.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gustavostolze.workshopmongo.domain.User;
import com.gustavostolze.workshopmongo.dto.UserDTO;
import com.gustavostolze.workshopmongo.repository.UserRepository;
import com.gustavostolze.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public User findById(String id) {
		Optional<User> obj = userRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found!"));
	}

	public User insert(User obj) {
		return userRepository.insert(obj);
	}

	public void delete(String id) {
		findById(id);
		userRepository.deleteById(id);
	}
	
	public User update(User obj) {
		User newObj = findById(obj.getId());
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
		return userRepository.save(newObj);
	}

	public User fromDTO(UserDTO objDto) {
		return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
	}
}
