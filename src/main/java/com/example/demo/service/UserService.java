package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repo.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository repository;
	
public List<User> getAllUsers(){
	
	List<User> userList=new ArrayList<>();
	repository.findAll().forEach(user->userList.add(user));
	return userList;
	
}

public User getUserById(Long id) {
	return repository.findById(id).get();
}

public boolean saveOrUpdateUser(User user) {
	User updatedUser =repository.save(user);
	
	if (repository.findById(updatedUser.getId())!=null) {
		return true;
	}
	return false;
}
//delete Service
public boolean deleteUser(Long id) {
	repository.deleteById(id);
	if (repository.findById(id)!=null) {
		return true;
	}
	return false;
	
}
}

