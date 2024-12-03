package com.twd.SpringSecurity.JWT.controller;

import com.twd.SpringSecurity.JWT.dto.RestaurantRequest;
import com.twd.SpringSecurity.JWT.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/uploadRestaurant")
@RequiredArgsConstructor
public class RestaurantController {
    private final RestaurantService restaurantService;

    @GetMapping
    public ResponseEntity<List<RestaurantRequest>> getAllRestaurants() {
        List<RestaurantRequest> restaurant = restaurantService.getAllRestaurants();
        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantRequest> getRestaurantById(@PathVariable Long id) {
        RestaurantRequest restaurant = restaurantService.getRestaurantById(id);
        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    @PostMapping("/upload")
    public ResponseEntity<Map<String, Object>> uploadRestaurantImage(@RequestParam("image") MultipartFile file,
                                                                     @RequestParam("address") String address,
                                                                     @RequestParam("numberphone") Integer numberphone,
                                                                     @RequestParam("kindOfFood") String kindOfFood,
                                                                     @RequestParam("resTauRantName") String resTauRantName) {
        try {
            RestaurantRequest restaurantRequest = new RestaurantRequest(address, numberphone, kindOfFood, resTauRantName);
            Map<String, Object> data = restaurantService.upload(file, restaurantRequest);
            return ResponseEntity.ok(data);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateRestaurant(
            @PathVariable Long id,
            @RequestParam("image") MultipartFile file,
            @RequestParam("address") String address,
            @RequestParam("numberphone") Integer numberphone,
            @RequestParam("kindOfFood") String kindOfFood,
            @RequestParam("resTauRantName") String resTauRantName) {
        try {

            RestaurantRequest restaurantRequest = new RestaurantRequest(address, numberphone, kindOfFood, resTauRantName);

            restaurantService.updateRestaurant(id, file, restaurantRequest);

            return ResponseEntity.ok(Map.of("message", "Restaurant updated successfully"));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", e.getMessage()));
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Failed to update image: " + e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteRestaurant(@PathVariable Long id) {
        try {
            restaurantService.deleteRestaurant(id);
            return new ResponseEntity<>(Map.of("message", "Image deleted"), HttpStatus.OK);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }catch (RuntimeException e) {
            return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
