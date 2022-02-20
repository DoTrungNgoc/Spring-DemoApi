package com.example.hellospringboot.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hellospringboot.model.Product;
import com.example.hellospringboot.model.ResponseObject;
import com.example.hellospringboot.repositories.ProductRespository;

@RestController
@RequestMapping(path = "api/v1/Products")
public class ProductController {
	@Autowired
	private ProductRespository respository;
	
	@GetMapping("/getAllProducts")
	//This request is: api/v1/P  roducts/getAllProducts
	List<Product> getAllProducts(){
		return respository.findAll();
				
	}
	
	
	// Get detail product
	@GetMapping("/{id}")
	ResponseEntity<ResponseObject> findById(@PathVariable Long id){
		Optional<Product> foundProduct = respository.findById(id);
		return foundProduct.isPresent()?
				ResponseEntity.status(HttpStatus.OK).body(
						new ResponseObject("ok", "Query product successfully", foundProduct)
					)
				:ResponseEntity.status(HttpStatus.NOT_FOUND).body(
						new ResponseObject("false", "Cannot find product with id = " + id, "")
						);
				
	}
	
	@PostMapping("/insert")
	ResponseEntity<ResponseObject> insertProduct(@RequestBody Product newProduct){
		List<Product> foundProducts = respository.findByProductName(newProduct.getProductName().trim());
		
		if (foundProducts.size()>0){
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
						new ResponseObject("failed", "Product name already taken", "")
					);
		}
		return ResponseEntity.status(HttpStatus.OK).body(
					new ResponseObject("ok", "Insert Producct successfully", respository.save(newProduct))
				);
	}
	
	@PutMapping("/{id}")
	ResponseEntity<ResponseObject> updateProduct(@RequestBody Product newProduct, @PathVariable Long id){
		Product updateProduct = respository.findById(id)
									.map(product->{
										product.setProductName(newProduct.getProductName());
										product.setYear(newProduct.getYear());
										product.setPrice(newProduct.getPrice());
										return respository.save(product);
									}).orElseGet(()->{
										newProduct.setId(id);
										return respository.save(newProduct);
									});
		return ResponseEntity.status(HttpStatus.OK).body(
					new ResponseObject("ok", "Update Product successfully", updateProduct)
				);
	}
	
	@DeleteMapping("/{id}")
	ResponseEntity<ResponseObject> deleteProduct(@PathVariable Long id){
		boolean exists = respository.existsById(id);
		if (exists) {
			respository.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).body(
						new ResponseObject("ok", "Delete product successfully", "")
					);
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
					new ResponseObject("failed", "Cannot find product to delete", "")
				);
		}
	}
}
