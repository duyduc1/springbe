package com.twd.SpringSecurity.JWT.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.twd.SpringSecurity.JWT.entity.OurUsers;
import com.twd.SpringSecurity.JWT.reponsitory.OurUserRepo;

@Service
public class UserService {
    @Autowired
    private OurUserRepo ourUserRepo;

    @Autowired
    private EmailService emailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void forgotPassword(String email) throws Exception {
        OurUsers user = ourUserRepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with Email " + email));
        String token = UUID.randomUUID().toString();
        user.setResetToken(token);
        user.setTokenExpirationDate(LocalDateTime.now().plusHours(1));
        ourUserRepo.save(user);
        String resetUrl = "http://localhost:3000/auth/reset-password?token=" + token;
        emailService.sendEmail(user.getEmail(), "Password Reset Request",
                "Click the link to reset your password: " + resetUrl);
    }

    public void resetPassword(String token, String password) throws Exception {
        OurUsers user = ourUserRepo.findByResetToken(token)
                .orElseThrow(() -> new IllegalArgumentException("Invailed Token"));
        if (user.getTokenExpirationDate().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Token has expried");
        }
        user.setPassword(passwordEncoder.encode(password));
        user.setResetToken(null);
        user.setTokenExpirationDate(null);
        ourUserRepo.save(user);
    }

}
