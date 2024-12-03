package com.twd.SpringSecurity.JWT.dto;

import lombok.Data;

import java.util.List;

@Data
public class RestaurantRequest {

    private Long id;
    private String url;
    private String publicId;
    private String resTauRantName;
    private String address;
    private Integer numberphone;
    private String kindOfFood;
    private List<FoodRequest> foodRequests;

    public RestaurantRequest(String address, Integer numberphone, String kindOfFood, String resTauRantName) {
        this.address = address;
        this.numberphone = numberphone;
        this.kindOfFood = kindOfFood;
        this.resTauRantName = resTauRantName;
    }

    public RestaurantRequest() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPublicId() {
        return publicId;
    }

    public void setPublicId(String publicId) {
        this.publicId = publicId;
    }

    public String getResTauRantName() {
        return resTauRantName;
    }

    public void setResTauRantName(String resTauRantName) {
        this.resTauRantName = resTauRantName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getNumberphone() {
        return numberphone;
    }

    public void setNumberphone(Integer numberphone) {
        this.numberphone = numberphone;
    }

    public String getKindOfFood() {
        return kindOfFood;
    }

    public void setKindOfFood(String kindOfFood) {
        this.kindOfFood = kindOfFood;
    }

    public List<FoodRequest> getFoodRequests() {
        return foodRequests;
    }

    public void setFoodRequests(List<FoodRequest> foodRequests) {
        this.foodRequests = foodRequests;
    }
}
