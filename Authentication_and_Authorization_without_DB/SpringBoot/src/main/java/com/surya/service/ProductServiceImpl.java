package com.surya.service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import com.surya.entity.Product;
import com.surya.repository.ProductRepository;



@Service
@Primary
public class ProductServiceImpl implements ProductService 
{
	
	
	@Autowired
	private ProductRepository productRepository;
	
	boolean flag;
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
	
	

	@Override
	public Product putUpdateProduct(Long id, Product product)
	{
	
		Product productfromDB = productRepository.findById(id).get();
			    productfromDB.setName(product.getName());
			    productfromDB.setPrice(product.getPrice());
			    productfromDB.setDepartment(product.getDepartment());
		return productRepository.save(productfromDB);
		
	}

	
	@Override
	public Product patchUpdateProduct(Long id, Map<String, Object> fields)
	{
		
        Optional<Product> existingProduct = productRepository.findById(id);

        if (existingProduct.isPresent()) 
        {
            fields.forEach((key, value) -> 
            {
                Field field = ReflectionUtils.findField(Product.class, key);
                field.setAccessible(true);
                ReflectionUtils.setField(field, existingProduct.get(), value);
            });
            return productRepository.save(existingProduct.get());
        }
        
        return null;
    }

	@Override
	public void deleteProduct(Long id) 
	{

		 productRepository.deleteById(id);
		
		
	}
	

}
