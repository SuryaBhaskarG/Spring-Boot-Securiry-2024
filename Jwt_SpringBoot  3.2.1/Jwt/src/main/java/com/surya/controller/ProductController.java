package com.surya.controller;



import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.surya.dto.AuthRequest;
import com.surya.entity.Product;
import com.surya.service.ProductService;
import com.surya.service.impl.JwtService;



@RestController
@RequestMapping("/products")
public class ProductController
{

	@Autowired
	private ProductService productService;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private AuthenticationManager authenticationManager;

	
	
	
	@PostMapping("/save")
	public ResponseEntity<Product> saveProduct(@RequestBody Product product)
	{
		return new ResponseEntity<Product> (productService.saveProduct(product), HttpStatus.CREATED);
	}

	@GetMapping("/fetchAll")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<List<Product>> fetchAllProducts()
	{
		return new ResponseEntity<List<Product>> (productService.fetchAllProducts(), HttpStatus.CREATED);
	}

	@GetMapping("/fetch/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
	public Product fetchProduct(@PathVariable Long id) 
	{
		return productService.fetchProduct(id);
	}
	
	
	
	 @PostMapping("/authenticate")
	  public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) 
	  {
		 
	     Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
	        
	        if (authentication.isAuthenticated()) 
	        {
	            return jwtService.generateToken(authRequest.getUsername());
	        } 
	        else 
	        {
	            throw new UsernameNotFoundException("invalid user request !");
	        }
	  }
	        
}
