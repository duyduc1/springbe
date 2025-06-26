package com.twd.SpringSecurity.JWT.controller;

import com.twd.SpringSecurity.JWT.dto.FoodRequest;
import com.twd.SpringSecurity.JWT.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class FoodController {

    private final FoodService foodService;

    @GetMapping("/api/useradminfood")
    public ResponseEntity<List<FoodRequest>> getAllFood() {
        List<FoodRequest> food = foodService.getFoodList();
        return new ResponseEntity<>(food, HttpStatus.OK);
    }

    @GetMapping("/api/useradminfood/{foodId}")
    public ResponseEntity<FoodRequest> getFoodById(@PathVariable Long foodId) {
        FoodRequest foodRequest = foodService.getFoodById(foodId);
        return new ResponseEntity<>(foodRequest, HttpStatus.OK);
    }

    @PostMapping("/api/uploadFood/uploadfood")
    public ResponseEntity<Map<String, Object>> uploadFood(
            @RequestParam(value = "image", required = false) MultipartFile file,
            @RequestParam(value = "foodName", required = false) String foodName,
            @RequestParam(value = "foodDescription", required = false) String foodDescription,
            @RequestParam(value = "foodPrice", required = false) Long foodPrice,
            @RequestParam(value = "restaurantId", required = false) Long restaurantId) {
        try {
            FoodRequest foodRequest = new FoodRequest(foodName, foodDescription, foodPrice, restaurantId);
            Map<String, Object> dataFood = foodService.uploadFood(file, foodRequest);
            return ResponseEntity.ok(dataFood);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/api/uploadFood/{foodId}")
    public ResponseEntity<Map<String, Object>> updateFood(
            @PathVariable Long foodId,
            @RequestParam(value = "image" , required = false) MultipartFile file,
            @RequestParam(value = "foodName" , required = false) String foodName,
            @RequestParam(value = "foodDescription" , required = false) String foodDescription,
            @RequestParam(value = "foodPrice" , required = false) Long foodPrice,
            @RequestParam(value = "restaurantId" , required = false) Long restaurantId) {
        try {

            FoodRequest foodRequest = new FoodRequest(foodName, foodDescription, foodPrice, restaurantId);

            foodService.updateFood(foodId, file, foodRequest);

            return ResponseEntity.ok(Map.of("message", "Restaurant updated successfully"));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", e.getMessage()));
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Failed to update image: " + e.getMessage()));
        }
    }

    @DeleteMapping("/api/uploadFood/{foodId}")
    public ResponseEntity<Map<String, Object>> deleteFood(@PathVariable Long foodId) {
        try {
            foodService.deleteFood(foodId);
            return new ResponseEntity<>(Map.of("message", "Image deleted"), HttpStatus.OK);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
