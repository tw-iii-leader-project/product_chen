package com.iii.shoppingcart.dao;

import org.springframework.data.repository.CrudRepository;

import com.iii.shoppingcart.domain.ShoppingCar;

public interface ShoppingCarDAO extends CrudRepository<ShoppingCar, Long>{

}
