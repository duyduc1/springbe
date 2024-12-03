package com.twd.SpringSecurity.JWT.controller;

import com.twd.SpringSecurity.JWT.dto.FoodRequest;
import com.twd.SpringSecurity.JWT.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/uploadFood")
@RequiredArgsConstructor
public class FoodController {

    private final FoodService foodService;

    @GetMapping
    public ResponseEntity<List<FoodRequest>> getAllFood() {
        List<FoodRequest> food = foodService.getFoodList();
        return new ResponseEntity<>(food, HttpStatus.OK);
    }

    @GetMapping("/{foodId}")
    public ResponseEntity<FoodRequest> getFoodById(@PathVariable Long foodId) {
        FoodRequest foodRequest = foodService.getFoodById(foodId);
        return new ResponseEntity<>(foodRequest, HttpStatus.OK);
    }

    @PostMapping("/uploadfood")
    public ResponseEntity<Map<String,Object>> uploadFood(@RequestParam("image")MultipartFile file,
                                                         @RequestParam("foodName") String foodName,
                                                         @RequestParam("foodDescription") String foodDescription,
                                                         @RequestParam("foodPrice") Long foodPrice,
                                                         @RequestParam("restaurantId") Long restaurantId){
        try {
            FoodRequest foodRequest = new FoodRequest(foodName, foodDescription, foodPrice, restaurantId);
            Map<String,Object> dataFood = foodService.uploadFood(file , foodRequest);
            return ResponseEntity.ok(dataFood);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
