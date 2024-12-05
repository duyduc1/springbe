package com.twd.SpringSecurity.JWT.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrdersRequest {
    private String OrderNumber;
    private Double OrderPrice;
    private String OrderStatus;
    private LocalDateTime orderDate;
    private Long userId;
    private String email;
    private Integer numberphone;
    private String name;

}
