package com.twd.SpringSecurity.JWT.controller;

import com.twd.SpringSecurity.JWT.dto.CartItemRequest;
import com.twd.SpringSecurity.JWT.entity.CartItem;
import com.twd.SpringSecurity.JWT.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/{cartItemId}")
    public ResponseEntity<String> updateCartItem(@PathVariable Long cartItemId, @RequestBody CartItemRequest cartItemRequest) {
        try {
            boolean isUpdated = cartItemService.updateCartItem(cartItemId, cartItemRequest);
            if (isUpdated) {
                return new ResponseEntity<>("success", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("fail", HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e) {
            return new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{cartItemId}")
    public ResponseEntity<String> deleteCartItem(@PathVariable Long cartItemId) {
        cartItemService.deleteCartItem(cartItemId);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }
}
