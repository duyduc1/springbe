package com.twd.SpringSecurity.JWT.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.twd.SpringSecurity.JWT.dto.ReqRes;
import com.twd.SpringSecurity.JWT.entity.Product;
import com.twd.SpringSecurity.JWT.reponsitory.ProductRepo;

@RestController
public class AdminUsers {

        @Autowired
        private ProductRepo productRepo;

        @GetMapping("/public/product")
        public ResponseEntity<Object> getAllproducts(){
            return ResponseEntity.ok(productRepo.findAll());
        }

        @PostMapping("/admin/saveproduct")
        public ResponseEntity<Object> signUp(@RequestBody ReqRes productRequest){
            Product productToSave = new Product();
            productToSave.setName(productRequest.getName());
            return ResponseEntity.ok(productRepo.save(productToSave));
        }

        @GetMapping("/user/alone")
        public ResponseEntity<Object> userAlone(){
            return ResponseEntity.ok("Users alone can access this Api only");
        }

        @GetMapping("/adminuser/both")
        public ResponseEntity<Object> bothAdminaAndUsersApi(){
            return ResponseEntity.ok("Both Admin and Users can access the api");
        }
}
