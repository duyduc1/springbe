package com.twd.SpringSecurity.JWT.Mapper;

import com.twd.SpringSecurity.JWT.dto.CartItemRequest;
import com.twd.SpringSecurity.JWT.dto.CartRequest;
import com.twd.SpringSecurity.JWT.entity.Cart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartMapper {

    @Mapping(source = "user.id", target = "userId")
    CartRequest toDto(Cart cart);

    @Mapping(source = "userId", target = "user.id")
    Cart toEntity(CartRequest cartRequest);

    CartItemRequest toCartItemRequest(Cart cart);
}
