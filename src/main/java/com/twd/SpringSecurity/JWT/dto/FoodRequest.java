package com.twd.SpringSecurity.JWT.dto;

public class FoodRequest {
    private Long foodId;
    private String foodName;
    private String foodDescription;
    private Long foodPrice;
    private String url;
    private String publicId;
    private Long restaurantId;
    private String resTauRantName;
    private String address;
    private Integer numberphone;
    private String kindOfFood;


    public FoodRequest() {

    }
    public FoodRequest(String foodName, String foodDescription, Long foodPrice , Long restaurantId) {
        this.foodName = foodName;
        this.foodDescription = foodDescription;
        this.foodPrice = foodPrice;
        this.restaurantId = restaurantId;
    }

    public Long getFoodId() {
        return foodId;
    }

    public void setFoodId(Long foodId) {
        this.foodId = foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodDescription() {
        return foodDescription;
    }

    public void setFoodDescription(String foodDescription) {
        this.foodDescription = foodDescription;
    }

    public Long getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(Long foodPrice) {
        this.foodPrice = foodPrice;
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

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
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
}

