package com.twd.SpringSecurity.JWT.Mapper;

import com.twd.SpringSecurity.JWT.dto.CartItemRequest;
import com.twd.SpringSecurity.JWT.entity.CartItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartItemMapper {
    CartItem toEntity(CartItemRequest cartItemRequest);
    CartItemRequest toDto(CartItem cartItem);
}
