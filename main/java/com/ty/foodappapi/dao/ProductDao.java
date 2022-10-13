package com.ty.foodappapi.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.foodappapi.dto.Product;
import com.ty.foodappapi.dto.User;
import com.ty.foodappapi.repository.ProductRepository;

@Repository
public class ProductDao {
	@Autowired
    ProductRepository productRepository;
	
public Product saveProduct(Product product) {
		return productRepository.save(product);
	}

public Product getProduct(int id) {
	Optional<Product> opt=productRepository.findById(id);
	if(opt.isPresent()) {
	  return opt.get();
	}
	else {
		return null;
	}
}
public Product updateProduct(int id,Product product) {
	Optional<Product> opt=productRepository.findById(id);
	if(opt.isPresent()) {
		Product ref=opt.get();
//		ref.setName(product.getName());
//		ref.setCost(product.getCost());
//		ref.setQuntity(product.getQuntity());
		//return productRepository.save(ref);
		return ref;
	}
	else {
		return null;
	}
}
public String deleteProduct(int id) {
	Optional<Product> opt =productRepository.findById(id);
	if(opt.isPresent()) {
	productRepository.delete(opt.get());
		return "deleted";
	}
	else {
		return null;
	}
}

public List<Product> getAll(){
	 return productRepository.findAll();
}

}
