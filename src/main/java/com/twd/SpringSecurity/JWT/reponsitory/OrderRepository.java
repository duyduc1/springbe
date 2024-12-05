package com.twd.SpringSecurity.JWT.reponsitory;

import com.twd.SpringSecurity.JWT.entity.Orders;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface OrderRepository extends JpaRepository<Orders , Long> {
    List<Orders> findByUserId(Long userId);
}
