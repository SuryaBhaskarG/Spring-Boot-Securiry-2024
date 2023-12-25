package com.surya.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.surya.entity.Product;
import com.surya.repository.ProductRepository;
import com.surya.service.ProductService;



@Service
public class ProductServiceImpl implements ProductService 
{
	
	
	@Autowired
	private ProductRepository productRepository;
	
	
	@Override
	public Product saveProduct(Product product) 
	{
		return productRepository.save(product);
	}
	
	

	@Override
	public List<Product> fetchAllProducts() 
	{
		return productRepository.findAll();
	}

	
	
	@Override
	public Product fetchProduct(Long id)  
	{
	    return productRepository.findById(id).get();
		
	}

}
