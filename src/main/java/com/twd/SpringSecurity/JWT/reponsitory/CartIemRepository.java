package com.twd.SpringSecurity.JWT.reponsitory;

import com.twd.SpringSecurity.JWT.entity.CartItem;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface CartIemRepository extends JpaRepository<CartItem , Long> {
}
