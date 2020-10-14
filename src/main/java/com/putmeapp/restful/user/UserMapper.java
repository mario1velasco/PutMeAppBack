package com.putmeapp.restful.user;

import java.util.List;

import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {
    UserDTO userToUserDTO(User user);

    List<UserDTO> userToUserDTOs(List<User> users);

    User userDTOToUser(UserDTO userDTO);

}
