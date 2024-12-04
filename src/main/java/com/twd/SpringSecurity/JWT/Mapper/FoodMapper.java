package com.twd.SpringSecurity.JWT.Mapper;

import com.twd.SpringSecurity.JWT.dto.FoodRequest;
import com.twd.SpringSecurity.JWT.entity.Food;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FoodMapper {

    @Mapping(source = "restaurant.id", target = "restaurantId")
    @Mapping(source = "restaurant.resTauRantName" , target = "resTauRantName")
    @Mapping(source = "restaurant.address" , target = "address")
    @Mapping(source = "restaurant.kindOfFood" , target = "kindOfFood")
    @Mapping(source = "restaurant.numberphone" , target = "numberphone")
    FoodRequest toDto(Food food);

    @Mapping(source = "restaurantId", target = "restaurant.id")
    Food toEntity(FoodRequest foodRequest);
}