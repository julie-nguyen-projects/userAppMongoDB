package com.epitech.pgt2019.service.mapper;

import com.epitech.pgt2019.domain.*;
import com.epitech.pgt2019.service.dto.UserDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity User and its DTO UserDTO.
 */
@Mapper(componentModel = "spring", uses = {CityMapper.class})
public interface UserMapper extends EntityMapper<UserDTO, User> {

    @Mapping(source = "city.id", target = "cityId")
    @Mapping(source = "city.name", target = "cityName")
    UserDTO toDto(User user);

    @Mapping(source = "cityId", target = "city")
    User toEntity(UserDTO userDTO);

    default User fromId(String id) {
        if (id == null) {
            return null;
        }
        User user = new User();
        user.setId(id);
        return user;
    }
}
