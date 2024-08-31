package com.twd.SpringSecurity.JWT.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.twd.SpringSecurity.JWT.entity.Product;

public interface ProductRepo extends JpaRepository<Product,Integer> {

}
