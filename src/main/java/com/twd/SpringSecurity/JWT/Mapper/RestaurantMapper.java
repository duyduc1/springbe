package com.twd.SpringSecurity.JWT.Mapper;

import com.twd.SpringSecurity.JWT.dto.FoodRequest;
import com.twd.SpringSecurity.JWT.dto.RestaurantRequest;
import com.twd.SpringSecurity.JWT.entity.Restaurant;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RestaurantMapper {

    Restaurant toEntity(RestaurantRequest restaurantRequest);
    RestaurantRequest toDTO(Restaurant restaurant);
    FoodRequest toFoodRequest(Restaurant restaurant);
}
