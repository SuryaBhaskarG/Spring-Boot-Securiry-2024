package com.surya.service;

import java.util.List;

import com.surya.entity.Product;

public interface ProductService
{
	
	public Product saveProduct(Product product);
	public List<Product> fetchAllProducts();
	public Product fetchProduct(Long id) ;
	
}
