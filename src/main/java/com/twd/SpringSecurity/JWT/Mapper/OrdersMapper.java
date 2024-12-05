package com.twd.SpringSecurity.JWT.Mapper;

import com.twd.SpringSecurity.JWT.dto.OrdersRequest;
import com.twd.SpringSecurity.JWT.entity.Orders;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrdersMapper {

    @Mapping(source = "user.numberphone" , target = "numberphone")
    @Mapping(source = "user.name" , target = "name")
    @Mapping(source = "user.email" , target = "email")
    @Mapping(source = "user.id" , target = "userId")
    OrdersRequest toDto(Orders orders);

    @Mapping(source = "userId" , target = "user.id")
    Orders toEntity(OrdersRequest ordersRequest);
}
