package com.twd.SpringSecurity.JWT.controller;

import com.twd.SpringSecurity.JWT.dto.CartItemRequest;
import com.twd.SpringSecurity.JWT.entity.CartItem;
import com.twd.SpringSecurity.JWT.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cartItem")
public class CartItemController {

    @Autowired
    private CartItemService cartItemService;

    @PostMapping
    public ResponseEntity<CartItem> addCartItem(@RequestBody CartItemRequest cartItemRequest) {
        CartItem cartItem = cartItemService.addCartItem(cartItemRequest);
        return new ResponseEntity<>(cartItem , HttpStatus.CREATED);
    }
}
