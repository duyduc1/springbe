package com.twd.SpringSecurity.JWT.reponsitory;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.twd.SpringSecurity.JWT.entity.OurUsers;

public interface OurUserRepo extends JpaRepository<OurUsers,Long> {
        Optional<OurUsers> findByEmail(String email);
        Optional<OurUsers> findByResetToken(String resetToken);
}
