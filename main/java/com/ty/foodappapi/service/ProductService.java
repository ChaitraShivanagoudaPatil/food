package com.ty.foodappapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.foodappapi.dao.ProductDao;
import com.ty.foodappapi.dto.Product;
import com.ty.foodappapi.dto.User;
import com.ty.foodappapi.exception.IdNotFoundException;
import com.ty.foodappapi.repository.ProductRepository;
import com.ty.foodappapi.respnsestru.ResponseStructure;

@Service
public class ProductService {
	@Autowired
	ProductDao productDao;
	@Autowired
	ProductRepository productRepository;
	
	public ResponseEntity<ResponseStructure<Product>>  saveProduct(Product product) {
		ResponseStructure<Product> responseStructure=new ResponseStructure<Product>();
		responseStructure.setStatus(HttpStatus.CREATED.value());
		responseStructure.setMessage("SUCESSFULLY CREATED");
		responseStructure.setData(productDao.saveProduct(product));
		return new ResponseEntity<ResponseStructure<Product>>(responseStructure,HttpStatus.CREATED);

	}
	public ResponseEntity<ResponseStructure<Product>> getProduct(int id){
		if(productDao.getProduct(id)!=null) {
		ResponseStructure<Product> responseStructure=new ResponseStructure<Product>();
		responseStructure.setStatus(HttpStatus.OK.value());
		responseStructure.setMessage("SUCESSFULL");
		 responseStructure.setData(productDao.getProduct(id));
		 return  new ResponseEntity<ResponseStructure<Product>>(responseStructure,HttpStatus.OK);
		}
		else {
			ResponseStructure<Product> responseStructure=new ResponseStructure<Product>();
			responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
			responseStructure.setMessage("invalid id");
			responseStructure.setData(null);
			return  new ResponseEntity<ResponseStructure<Product>>(responseStructure,HttpStatus.NOT_FOUND);
		}
	}
	
	public ResponseEntity<ResponseStructure<Product>> updateProduct(int id,Product product){
		Optional<Product> opt=productRepository.findById(id);
		 ResponseStructure<Product> responseStructure=new ResponseStructure<Product>();
		   if(opt.isPresent()) {
			   Product ref=opt.get();
				ref.setName(product.getName());
				ref.setCost(product.getCost());
				ref.setQuntity(product.getQuntity());
				//return productRepository.save(ref);
				responseStructure.setStatus(HttpStatus.OK.value());
				responseStructure.setMessage("SUCESSFULL");
				 responseStructure.setData(productDao.saveProduct(ref));
			   return new ResponseEntity<ResponseStructure<Product>>(responseStructure,HttpStatus.OK) ;
		   }else {
			   throw new IdNotFoundException("Id "+id+" doesn't exist");
		   }
		}
	
	public ResponseEntity<ResponseStructure<String>> deleteProduct(int id){
		if(productDao.deleteProduct(id)!=null) {
			ResponseStructure<String> responseStructure=new ResponseStructure<String>();
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("SUCESSFULL");
			responseStructure.setData(productDao.deleteProduct(id));
			return new ResponseEntity<ResponseStructure<String>>(responseStructure,HttpStatus.OK);
		}
		else {
//			ResponseStructure<String> responseStructure=new ResponseStructure<String>();
//			responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
//			responseStructure.setMessage("invalid id");
//			responseStructure.setData(null);
//			return responseStructure;
			throw new IdNotFoundException("Id "+id+" doesn't exist");
			
		}
		
	}
	public ResponseEntity<ResponseStructure<List<Product>>> getAll(){
		ResponseStructure<List<Product>> responseStructure=new ResponseStructure<List<Product>>();
		responseStructure.setStatus(HttpStatus.OK.value());
		responseStructure.setMessage("SUCESSFULL");
		responseStructure.setData(productDao.getAll());
		return new ResponseEntity<ResponseStructure<List<Product>>>(responseStructure,HttpStatus.OK);
		
	}
	
	
}
