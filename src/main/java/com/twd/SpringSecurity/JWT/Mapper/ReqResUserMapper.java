package com.twd.SpringSecurity.JWT.Mapper;

import com.twd.SpringSecurity.JWT.dto.ReqRes;
import com.twd.SpringSecurity.JWT.entity.OurUsers;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReqResUserMapper {
    OurUsers toEntity(ReqRes reqResUser);
    ReqRes toDTO(OurUsers ourUsers);
}
