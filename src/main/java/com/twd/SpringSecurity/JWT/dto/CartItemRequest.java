package com.twd.SpringSecurity.JWT.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItemRequest {
    private Long cartItemId;
    private int quantity;
    private Long foodId;
    private String foodName;
    private double foodPrice;
    private String foodDescription;
    private String url;
    private String publicId;
    private Long cartId;
}
