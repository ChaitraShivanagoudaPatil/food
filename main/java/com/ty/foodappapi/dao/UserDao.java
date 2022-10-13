package com.ty.foodappapi.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.foodappapi.dto.User;
import com.ty.foodappapi.repository.UserRepository;


@Repository
public class UserDao {
	@Autowired
	UserRepository userRepository;
	public User saveUser(User user) {
		
		return userRepository.save(user);
	}
	
	public User getuser(int id) {
		Optional<User> opt=userRepository.findById(id);
		if(opt.isPresent()) {
		  return opt.get();
		}
		else {
			return null;
		}
	}
	
	public User updateUser(int id,User user) {
		Optional<User> opt=userRepository.findById(id);
		if(opt.isPresent()) {
			User ref=opt.get();
			return ref;
			
		}
		else {
			return null;
		}
	}
		public String deleteUser(int id) {
			Optional<User> opt =userRepository.findById(id);
			if(opt.isPresent()) {
				userRepository.delete(opt.get());
				return "deleted";
			}
			else {
				return null;
			}
		}
		
		public User validateUser(String email,String password) {
			User user =userRepository.findByEmailAndPassword(email, password);
			return user;
			
		}
		
		public List<User> getAll(){
			 return userRepository.findAll();
		}
	}
	


