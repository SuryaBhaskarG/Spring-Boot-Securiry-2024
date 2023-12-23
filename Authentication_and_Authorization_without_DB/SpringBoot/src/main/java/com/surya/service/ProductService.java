package com.surya.service;

import java.util.List;
import java.util.Map;

import com.surya.entity.Product;


public interface ProductService
{
	
	public Product saveProduct(Product product);
	public List<Product> fetchAllProducts();
	public Product fetchProduct(Long id) ;
	public Product putUpdateProduct(Long id, Product product);
	public Product patchUpdateProduct(Long id, Map<String, Object> productMap);
	public void deleteProduct(Long id);
	
}
