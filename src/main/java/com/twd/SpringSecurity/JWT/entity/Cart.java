package com.twd.SpringSecurity.JWT.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;

    @OneToOne
    @JoinColumn(name = "user_id" , referencedColumnName = "id")
    @JsonIgnore
    private OurUsers user;

    @OneToMany(mappedBy = "cart" ,cascade = CascadeType.ALL,orphanRemoval = true)
    private List<CartItem> cartItems;

    public List<CartItem> getCartItems() {
        return cartItems;
    }

}
