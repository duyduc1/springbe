package com.twd.SpringSecurity.JWT.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.twd.SpringSecurity.JWT.Mapper.FoodMapper;
import com.twd.SpringSecurity.JWT.Mapper.RestaurantMapper;
import com.twd.SpringSecurity.JWT.dto.FoodRequest;
import com.twd.SpringSecurity.JWT.dto.RestaurantRequest;
import com.twd.SpringSecurity.JWT.entity.Restaurant;
import com.twd.SpringSecurity.JWT.reponsitory.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final Cloudinary cloudinary;
    private final RestaurantRepository restaurantRepository;

    @Autowired
    private FoodMapper foodMapper;

    @Autowired
    private RestaurantMapper restaurantMapper;

    public List<RestaurantRequest> getAllRestaurants() {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        return restaurants.stream()
                .map(restaurant -> {
                    RestaurantRequest restaurantRequest = restaurantMapper.toDTO(restaurant);

                    List<FoodRequest> foodRequests = restaurant.getFoodList().stream()
                            .map(foodMapper::toDto)
                            .collect(Collectors.toList());

                    restaurantRequest.setFoodRequests(foodRequests);

                    return restaurantRequest;
                })
                .collect(Collectors.toList());
    }


    public RestaurantRequest getRestaurantById(Long id) {
        Restaurant restaurant = restaurantRepository.findById(id).orElse(null);
        return restaurantMapper.toDTO(restaurant);
    }

    public Map<String , Object> upload(MultipartFile file, RestaurantRequest restaurantUploadDTO) {
        try {
            Map<String,Object> data = this.cloudinary.uploader().upload(file.getBytes(),Map.of());
            Restaurant restaurant = new Restaurant();
            restaurant.setUrl((String) data.get("url"));
            restaurant.setPublicId((String) data.get("public_id"));
            restaurant.setAddress(restaurantUploadDTO.getAddress());
            restaurant.setNumberphone(restaurantUploadDTO.getNumberphone());
            restaurant.setKindOfFood(restaurantUploadDTO.getKindOfFood());
            restaurant.setResTauRantName(restaurantUploadDTO.getResTauRantName());
            restaurantRepository.save(restaurant);
            return data;
        }catch (IOException e) {
            throw new RuntimeException("Failed to upload file: " + e.getMessage(), e);
        }
    }

    public void updateRestaurant(Long id , MultipartFile file, RestaurantRequest restaurantRequest) throws IOException {
        Restaurant restaurant = restaurantRepository.findById(id).orElse(null);
        cloudinary.uploader().destroy(restaurant.getPublicId(), ObjectUtils.emptyMap());
        Map<String , Object> newData = this.cloudinary.uploader().upload(file.getBytes(),Map.of());
        restaurant.setUrl((String) newData.get("url"));
        restaurant.setPublicId((String) newData.get("public_id"));
        restaurant.setAddress(restaurantRequest.getAddress());
        restaurant.setNumberphone(restaurantRequest.getNumberphone());
        restaurant.setKindOfFood(restaurantRequest.getKindOfFood());
        restaurant.setResTauRantName(restaurantRequest.getResTauRantName());
        restaurantRepository.save(restaurant);
    }

    public void deleteRestaurant(Long id)  throws IOException {
        Restaurant restaurant = restaurantRepository.findById(id).orElse(null);
        cloudinary.uploader().destroy(restaurant.getPublicId(), ObjectUtils.emptyMap());
        restaurantRepository.delete(restaurant);
    }

    public Restaurant findById(Long id) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        return restaurant.orElse(null);
    }

}
