package com.twd.SpringSecurity.JWT.reponsitory;

import com.twd.SpringSecurity.JWT.entity.Food;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface FoodRepository extends JpaRepository<Food, Long> {
}
