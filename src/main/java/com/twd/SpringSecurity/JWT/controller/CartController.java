package com.twd.SpringSecurity.JWT.controller;

import com.twd.SpringSecurity.JWT.dto.CartRequest;
import com.twd.SpringSecurity.JWT.entity.Cart;
import com.twd.SpringSecurity.JWT.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<CartRequest> getCartByUser(@PathVariable Long userId) {
        CartRequest cartRequest = cartService.getCartByUserId(userId);
        return new ResponseEntity<>(cartRequest, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Cart> addCart(@RequestBody CartRequest cartRequest) {
        Cart cart = cartService.addCart(cartRequest);
        return new ResponseEntity<>(cart, HttpStatus.CREATED);
    }
}