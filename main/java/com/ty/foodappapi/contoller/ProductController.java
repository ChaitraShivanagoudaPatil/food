package com.ty.foodappapi.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ty.foodappapi.dto.Product;
import com.ty.foodappapi.dto.User;
import com.ty.foodappapi.respnsestru.ResponseStructure;
import com.ty.foodappapi.service.ProductService;

@RestController
public class ProductController {
	@Autowired
	ProductService productService;
	
	@PostMapping("/products")
	public ResponseEntity<ResponseStructure<Product>> saveUser(@RequestBody Product product){
		return productService.saveProduct(product);
	}

	@GetMapping("/products/{id}")
	public ResponseEntity<ResponseStructure<Product>> getUsers(@PathVariable int id){
		return productService.getProduct(id);
	}
	
	@PutMapping("/products")
	public ResponseEntity<ResponseStructure<Product>> getUsers(@RequestParam int id,@RequestBody Product product){
		return productService.updateProduct(id, product);
	}
	@DeleteMapping("/products")
	public ResponseEntity<ResponseStructure<String>> deleteProduct(@RequestParam int id){
		return productService.deleteProduct(id);
	}
	
	@GetMapping("/products")
	public ResponseEntity<ResponseStructure<List<Product>>> getAll(){
		return productService.getAll();
	}

}
