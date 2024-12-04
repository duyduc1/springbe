package com.twd.SpringSecurity.JWT.service;

import com.twd.SpringSecurity.JWT.Mapper.CartItemMapper;
import com.twd.SpringSecurity.JWT.Mapper.CartMapper;
import com.twd.SpringSecurity.JWT.dto.CartItemRequest;
import com.twd.SpringSecurity.JWT.entity.Cart;
import com.twd.SpringSecurity.JWT.entity.CartItem;
import com.twd.SpringSecurity.JWT.entity.Food;
import com.twd.SpringSecurity.JWT.entity.OurUsers;
import com.twd.SpringSecurity.JWT.reponsitory.CartIemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartItemService {

    @Autowired
    private CartIemRepository cartIemRepository;

    @Autowired
    private FoodService foodService;

    @Autowired
    private CartService cartService;

    @Autowired
    private CartItemMapper cartItemMapper;

    public List<CartItemRequest> getAllCartItems() {
        List<CartItem> listCartItems = cartIemRepository.findAll();
        return listCartItems.stream()
                .map(cartItemMapper::toDto)
                .collect(Collectors.toList());
    }

    public CartItemRequest getCartItemById(Long cartItemId) {
        CartItem cartItem = cartIemRepository.findById(cartItemId).orElse(null);
        return cartItemMapper.toDto(cartItem);
    }

    public CartItem addCartItem(CartItemRequest cartItemRequest) {
        CartItem cartItem = new CartItem();
        cartItem.setCartItemId(cartItemRequest.getCartItemId());
        cartItem.setQuantity(cartItemRequest.getQuantity());
        Food food = foodService.findById(cartItemRequest.getFoodId());
        Cart cart = cartService.findById(cartItemRequest.getCartId());
        cartItem.setFood(food);
        cartItem.setCart(cart);
        return cartIemRepository.save(cartItem);
    }

    public boolean updateCartItem(Long cartItemId , CartItemRequest cartItemRequest) {
        CartItem cartItem = cartIemRepository.findById(cartItemId).orElse(null);
        cartItem.setQuantity(cartItemRequest.getQuantity());
        cartIemRepository.save(cartItem);
        return true;
    }

    public boolean deleteCartItem(Long cartItemId) {
        CartItem cartItem = cartIemRepository.findById(cartItemId).orElse(null);
        cartIemRepository.delete(cartItem);
        return true;
    }

}
