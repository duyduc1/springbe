package com.twd.SpringSecurity.JWT.Mapper;

import com.twd.SpringSecurity.JWT.dto.CartItemRequest;
import com.twd.SpringSecurity.JWT.dto.CartRequest;
import com.twd.SpringSecurity.JWT.entity.Cart;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartMapper {
    Cart toEntity(CartRequest cartRequest);
    CartRequest toDto(Cart cart);
    CartItemRequest toCartItemRequest(Cart cart);
}
