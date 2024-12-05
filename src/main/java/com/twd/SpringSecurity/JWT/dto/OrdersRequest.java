package com.twd.SpringSecurity.JWT.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrdersRequest {
    @JsonProperty("OrderNumber")
    private String orderNumber;

    @JsonProperty("OrderPrice")
    private Double orderPrice;

    @JsonProperty("OrderStatus")
    private String orderStatus;

    @JsonProperty("Localtion")
    private String localtion;

    private Long orderId;

    private Long userId;
    private String email;
    private Integer numberphone;
    private String name;

}