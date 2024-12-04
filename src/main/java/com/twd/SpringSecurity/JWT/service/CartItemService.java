package com.twd.SpringSecurity.JWT.service;

import com.twd.SpringSecurity.JWT.Mapper.CartMapper;
import com.twd.SpringSecurity.JWT.dto.CartItemRequest;
import com.twd.SpringSecurity.JWT.entity.CartItem;
import com.twd.SpringSecurity.JWT.entity.Food;
import com.twd.SpringSecurity.JWT.reponsitory.CartIemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartItemService {

    @Autowired
    private CartIemRepository cartIemRepository;

    @Autowired
    private FoodService foodService;

    @Autowired
    private CartMapper cartMapper;

    public CartItem addCartItem(CartItemRequest cartItemRequest) {
        CartItem cartItem = new CartItem();
        cartItem.setCartItemId(cartItemRequest.getCartItemId());
        cartItem.setQuantity(cartItemRequest.getQuantity());
        Food food = foodService.findById(cartItemRequest.getFoodId());
        cartItem.setFood(food);
        return cartIemRepository.save(cartItem);
    }
}
