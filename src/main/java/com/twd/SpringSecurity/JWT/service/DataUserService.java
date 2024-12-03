package com.twd.SpringSecurity.JWT.service;

import com.twd.SpringSecurity.JWT.Mapper.ReqResUserMapper;
import com.twd.SpringSecurity.JWT.dto.ReqRes;
import com.twd.SpringSecurity.JWT.entity.OurUsers;
import com.twd.SpringSecurity.JWT.reponsitory.OurUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DataUserService {

    @Autowired
    private OurUserRepo ourUserRepository;

    @Autowired
    private ReqResUserMapper reqResUserMapper;

    public List<ReqRes> getAllUsers() {
        List<OurUsers> users = ourUserRepository.findAll();
        return users.stream()
                .map(reqResUserMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ReqRes getUserById(Long id) {
        OurUsers ourUsers = ourUserRepository.findById(id).orElse(null);
        return reqResUserMapper.toDTO(ourUsers);
    }

    public boolean updateUser(Long id , OurUsers ourUsers) {
        OurUsers ourUser = ourUserRepository.findById(id).orElse(null);
        if (ourUser != null) {
            ourUser.setName(ourUsers.getName());
            ourUser.setNumberphone(ourUsers.getNumberphone());
            ourUser.setEmail(ourUsers.getEmail());
            ourUserRepository.save(ourUser);
            return true;
        }
        return false;
    }

    public boolean deleteUser(Long id) {
        OurUsers ourUsers = ourUserRepository.findById(id).orElse(null);
        if (ourUsers != null) {
            ourUserRepository.delete(ourUsers);
        }
        return true;
    }

}
