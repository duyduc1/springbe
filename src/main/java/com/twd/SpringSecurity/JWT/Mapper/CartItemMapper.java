package com.twd.SpringSecurity.JWT.Mapper;

import com.twd.SpringSecurity.JWT.dto.CartItemRequest;
import com.twd.SpringSecurity.JWT.entity.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartItemMapper {

    @Mapping(source = "foodId", target = "food.foodId")
    @Mapping(source = "cartId" ,target = "cart.cartId")
    CartItem toEntity(CartItemRequest cartItemRequest);

    @Mapping(source = "food.foodId", target = "foodId")
    @Mapping(source = "food.foodName", target = "foodName")
    @Mapping(source = "food.foodDescription", target = "foodDescription")
    @Mapping(source = "food.foodPrice", target = "foodPrice")
    @Mapping(source = "food.url" , target = "url")
    @Mapping(source = "food.publicId" , target = "publicId")
    @Mapping(source = "cart.cartId" , target = "cartId")
    CartItemRequest toDto(CartItem cartItem);
}
