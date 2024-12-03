package com.twd.SpringSecurity.JWT.Mapper;

import com.twd.SpringSecurity.JWT.dto.FoodRequest;
import com.twd.SpringSecurity.JWT.entity.Food;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FoodMapper {

    @Mapping(source = "restaurant.id", target = "restaurantId")
    FoodRequest toDto(Food food);

    @Mapping(source = "restaurantId", target = "restaurant.id")
    Food toEntity(FoodRequest foodRequest);
}