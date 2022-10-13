package com.ty.foodappapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.foodappapi.dao.UserDao;
import com.ty.foodappapi.dto.Login;
import com.ty.foodappapi.dto.User;
import com.ty.foodappapi.exception.IdNotFoundException;
import com.ty.foodappapi.repository.UserRepository;
import com.ty.foodappapi.respnsestru.ResponseStructure;

@Service
public class UserService {
	@Autowired
	UserDao userDao;
	@Autowired
	UserRepository userRepository;
	public ResponseEntity<ResponseStructure<User>> saveUser(User user) {
		ResponseStructure<User> responseStructure=new ResponseStructure<User>();
		responseStructure.setStatus(HttpStatus.CREATED.value());
		responseStructure.setMessage("SUCESSFULLY CREATED");
		responseStructure.setData(userDao.saveUser(user));
		return new ResponseEntity<ResponseStructure<User>>(responseStructure,HttpStatus.CREATED);
//		return userDao.saveUser(user);
	}
	
	public ResponseEntity<ResponseStructure<User>> getuser(int id){
//		User user=userDao.getuser(id);
		if(userDao.getuser(id)!=null) {
		ResponseStructure<User> responseStructure=new ResponseStructure<User>();
		responseStructure.setStatus(HttpStatus.OK.value());
		responseStructure.setMessage("SUCESSFULL");
		 responseStructure.setData(userDao.getuser(id));
		 return new ResponseEntity<ResponseStructure<User>>(responseStructure,HttpStatus.OK);
		}
		else {
			ResponseStructure<User> responseStructure=new ResponseStructure<User>();
			responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
			responseStructure.setMessage("invalid id");
			responseStructure.setData(null);
			return new ResponseEntity<ResponseStructure<User>>(responseStructure,HttpStatus.NOT_FOUND);
		}
	}
	public ResponseEntity<ResponseStructure<User>> updateUser(int id,User user){
		Optional<User> opt=userRepository.findById(id);
		ResponseStructure<User> responseStructure=new ResponseStructure<User>();
	   if(opt.isPresent()) {
		  User ref=opt.get();
		   ref.setPassword(user.getPassword());
			ref.setGender(user.getGender());
			ref.setEmail(user.getEmail());
			ref.setPhone(user.getPhone());
			ref.setRole(user.getRole());
			ref.setName(user.getName());
		   
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("SUCESSFULL");
			 responseStructure.setData(userDao.saveUser(ref));
		   return new ResponseEntity<ResponseStructure<User>>(responseStructure,HttpStatus.OK) ;
	   }else {
		   throw new IdNotFoundException("Id "+id+" doesn't exist");
	   }
	}
	
	public ResponseEntity<ResponseStructure<String>> deleteUser(int id){
		if(userDao.deleteUser(id)!=null) {
			ResponseStructure<String> responseStructure=new ResponseStructure<String>();
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("SUCESSFULL");
			responseStructure.setData(userDao.deleteUser(id));
			return new ResponseEntity<ResponseStructure<String>>(responseStructure,HttpStatus.OK) ;
		}
		else {
			ResponseStructure<String> responseStructure=new ResponseStructure<String>();
			responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
			responseStructure.setMessage("invalid id");
			responseStructure.setData(null);
			return new ResponseEntity<ResponseStructure<String>>(responseStructure,HttpStatus.NOT_FOUND) ;
			
		}
	}
	public ResponseEntity<ResponseStructure<User>> validateUser(Login login){
		User user=userDao.validateUser(login.getEmail(), login.getPassword());
		if(user!=null) {
			//valid user
		ResponseStructure<User> responseStructure=new ResponseStructure<User>();
		responseStructure.setStatus(HttpStatus.OK.value());
		responseStructure.setMessage("SUCESSFULL");
		responseStructure.setData(user);
		return new ResponseEntity<ResponseStructure<User>>(responseStructure,HttpStatus.OK);
		}else {
			//not valid user
			ResponseStructure<User> responseStructure=new ResponseStructure<User>();
			responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
			responseStructure.setMessage("invalid Credential");
			responseStructure.setData(null);
			return new ResponseEntity<ResponseStructure<User>>(responseStructure,HttpStatus.NOT_FOUND);
		}
		
	}
	public ResponseEntity<ResponseStructure<List<User>>> getAll(){
		ResponseStructure<List<User>> responseStructure=new ResponseStructure<List<User>>();
		responseStructure.setStatus(HttpStatus.OK.value());
		responseStructure.setMessage("SUCESSFULL");
		responseStructure.setData(userDao.getAll());
		return new ResponseEntity<ResponseStructure<List<User>>>(responseStructure,HttpStatus.OK);
		
	}
	

}
