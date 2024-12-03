package com.twd.SpringSecurity.JWT.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "restaurant")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String url;
    private String publicId;
    private String resTauRantName;
    private String address;
    private Integer numberphone;
    private String kindOfFood;

    @OneToMany(mappedBy = "restaurant" , cascade = CascadeType.ALL)
    private List<Food> foodList;

    public List<Food> getFoodList() {
        return foodList;
    }
}
