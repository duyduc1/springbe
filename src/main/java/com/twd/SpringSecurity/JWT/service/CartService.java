package com.twd.SpringSecurity.JWT.service;

import com.twd.SpringSecurity.JWT.Mapper.CartItemMapper;
import com.twd.SpringSecurity.JWT.Mapper.CartMapper;
import com.twd.SpringSecurity.JWT.dto.CartItemRequest;
import com.twd.SpringSecurity.JWT.dto.CartRequest;
import com.twd.SpringSecurity.JWT.entity.Cart;
import com.twd.SpringSecurity.JWT.entity.OurUsers;
import com.twd.SpringSecurity.JWT.reponsitory.CartIemRepository;
import com.twd.SpringSecurity.JWT.reponsitory.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

public class CartService {

    @Autowired
    private  CartRepository cartRepository;

    @Autowired
    private DataUserService dataUserService;

    @Autowired
    private CartIemRepository cartIemRepository;

    @Autowired
    private CartItemMapper cartItemMapper;

    @Autowired
    private CartMapper cartMapper;

    public Cart addCart(CartRequest cartRequest) {
        Cart cart = new Cart();
        OurUsers ourUser = dataUserService.findById(cartRequest.getUserId());
        cart.setUser(ourUser);
        return cartRepository.save(cart);
    }

    public CartRequest getCartByCartId(Long cartId) {
        Cart cart = cartRepository.findById(cartId).orElse(null);
        if (cart == null) {
            return null;
        }
        CartRequest cartRequest = cartMapper.toDto(cart);

        List<CartItemRequest> cartItemRequests = cart.getCartItems().stream()
                .map(cartItemMapper::toDto)
                .collect(Collectors.toList());
        cartRequest.setCartItemRequests(cartItemRequests);
        return cartRequest;
    }


    public void clearCart(Long cartId) {
        Cart cart = cartRepository.findById(cartId).orElse(null);
        cartIemRepository.deleteAll(cart.getCartItems());
    }

    public Cart findById(Long cartId) {
        Optional<Cart> cart = cartRepository.findById(cartId);
        return cart.orElse(null);
    }
}
