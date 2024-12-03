package com.twd.SpringSecurity.JWT.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.twd.SpringSecurity.JWT.Mapper.FoodMapper;
import com.twd.SpringSecurity.JWT.dto.FoodRequest;
import com.twd.SpringSecurity.JWT.entity.Food;
import com.twd.SpringSecurity.JWT.entity.Restaurant;
import com.twd.SpringSecurity.JWT.reponsitory.FoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FoodService {

    private final FoodRepository foodRepository;
    private final Cloudinary cloudinary;

    @Autowired
    private FoodMapper foodMapper;

    @Autowired
    private RestaurantService restaurantService;

    public List<FoodRequest> getFoodList() {
        List<Food> foodList = foodRepository.findAll();
        return foodList.stream()
                .map(foodMapper::toDto)
                .collect(Collectors.toList());
    }

    public FoodRequest getFoodById(Long foodId){
        Food food = foodRepository.findById(foodId).orElse(null);
        return foodMapper.toDto(food);
    }

    public Map<String , Object> uploadFood(MultipartFile file , FoodRequest foodRequest){
        try {
            java.util.Map<String,Object> dataFood = this.cloudinary.uploader().upload(file.getBytes(), java.util.Map.of());
            Food food = new Food();
            food.setUrl((String) dataFood.get("url"));
            food.setPublicId((String) dataFood.get("public_id"));
            food.setFoodDescription(foodRequest.getFoodDescription());
            food.setFoodName(foodRequest.getFoodName());
            food.setFoodPrice(foodRequest.getFoodPrice());
            Restaurant restaurant = restaurantService.findById(foodRequest.getRestaurantId());
            food.setRestaurant(restaurant);
            foodRepository.save(food);
            return dataFood;
        }catch (IOException e){
            throw new RuntimeException("Failed" + e.getMessage() , e);
        }
    }

    public void updateFood(Long foodId , MultipartFile file , FoodRequest foodRequest) throws IOException {
        Food food = foodRepository.findById(foodId).orElse(null);
        cloudinary.uploader().destroy(food.getPublicId(), ObjectUtils.emptyMap());
        Map<String,Object> newDataFood = this.cloudinary.uploader().upload(file.getBytes(), java.util.Map.of());
        food.setUrl((String) newDataFood.get("url"));
        food.setPublicId((String) newDataFood.get("public_id"));
        food.setFoodDescription(foodRequest.getFoodDescription());
        food.setFoodName(foodRequest.getFoodName());
        food.setFoodPrice(foodRequest.getFoodPrice());
        foodRepository.save(food);
    }

    public void deleteFood(Long foodId) throws IOException {
        Food food = foodRepository.findById(foodId).orElse(null);
        cloudinary.uploader().destroy(food.getPublicId(), ObjectUtils.emptyMap());
        foodRepository.delete(food);
    }
}
