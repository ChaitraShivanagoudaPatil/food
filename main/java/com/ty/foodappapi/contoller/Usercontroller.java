package com.ty.foodappapi.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ty.foodappapi.dto.Login;
import com.ty.foodappapi.dto.User;
import com.ty.foodappapi.respnsestru.ResponseStructure;
import com.ty.foodappapi.service.UserService;

@RestController
public class Usercontroller {
	@Autowired
	UserService userService ;
	
	@PostMapping("/users")
	public ResponseEntity<ResponseStructure<User>> saveUser(@RequestBody User user){
		return userService.saveUser(user);
	}
	
	@GetMapping("/users/{id}")
	public ResponseEntity<ResponseStructure<User>> getUsers(@PathVariable int id){
	
		return userService.getuser(id);
	}
	
	@PutMapping("/users")
	public ResponseEntity<ResponseStructure<User>> getUsers(@RequestParam int id,@RequestBody User user){
		return userService.updateUser(id, user);
	}
	
	@DeleteMapping("/users")
	public ResponseEntity<ResponseStructure<String>> deleteUser(@RequestParam int id){
		return userService.deleteUser(id);
		
	}
	
	@PostMapping("/users/login")
	public ResponseEntity<ResponseStructure<User>> validateUser(@RequestBody Login login){
		return userService.validateUser(login);
		
	}
	@GetMapping("/users")
	public ResponseEntity<ResponseStructure<List<User>>> getAll(){
		return userService.getAll();
	}
	
	

}
