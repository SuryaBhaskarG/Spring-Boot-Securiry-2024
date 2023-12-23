package com.surya.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.surya.entity.Product;


public interface ProductRepository extends JpaRepository<Product, Long>
{

}
