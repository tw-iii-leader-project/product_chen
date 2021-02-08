package com.iii.shoppingcart.dao;

import org.springframework.data.repository.CrudRepository;

import com.iii.shoppingcart.domain.Product;

public interface ProductDAO extends CrudRepository<Product, Long>{

}
