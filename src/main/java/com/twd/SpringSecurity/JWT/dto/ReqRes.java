package com.twd.SpringSecurity.JWT.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.twd.SpringSecurity.JWT.entity.OurUsers;
import com.twd.SpringSecurity.JWT.entity.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class ReqRes {
    private int statusCode;
    private String error;
    private String message;
    private String token;
    private String refreshToken;
    private String expirationTime;
    private String name;
    private Long id;
    private String email;
    private Integer numberphone;
    private String username;
    private String role;
    private String password;
    private List<Product> products;
    private OurUsers ourUsers;
    private Long cartId ;
}
