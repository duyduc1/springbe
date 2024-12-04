package com.twd.SpringSecurity.JWT.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartRequest {
    private Long cartId;
    private Long userId;
    private List<CartItemRequest> cartItemRequests;
}
