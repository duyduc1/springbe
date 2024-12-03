package com.twd.SpringSecurity.JWT.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "cartItem")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartItemId;
    private int quantity;
    @ManyToOne
    @JoinColumn(name = "cart_id" , referencedColumnName = "cartId")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "food_id" , referencedColumnName = "foodId")
    private Food food;
}
