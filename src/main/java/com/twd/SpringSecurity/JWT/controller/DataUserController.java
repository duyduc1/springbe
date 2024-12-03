package com.twd.SpringSecurity.JWT.controller;

import com.twd.SpringSecurity.JWT.dto.ReqRes;
import com.twd.SpringSecurity.JWT.entity.OurUsers;
import com.twd.SpringSecurity.JWT.service.DataUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class DataUserController {

    @Autowired
    private DataUserService dataUserService;

    @GetMapping
    public ResponseEntity<List<ReqRes>> getAllUsers() {
        List<ReqRes> users = dataUserService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReqRes> getUserById(@PathVariable("id") Long id) {
        ReqRes userId = dataUserService.getUserById(id);
        return new ResponseEntity<>(userId, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> updateUser(@PathVariable("id") Long id, @RequestBody OurUsers user) {
        boolean result = dataUserService.updateUser(id, user);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id) {
        boolean result = dataUserService.deleteUser(id);
        return new ResponseEntity<>(  "Ok" , HttpStatus.OK);
    }
}
