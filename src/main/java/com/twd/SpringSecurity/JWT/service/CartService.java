package com.twd.SpringSecurity.JWT.service;

import com.twd.SpringSecurity.JWT.entity.Cart;
import com.twd.SpringSecurity.JWT.reponsitory.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;

    public Cart findById(Long cartId) {
        Optional<Cart> cart = cartRepository.findById(cartId);
        return cart.orElse(null);
    }
}
