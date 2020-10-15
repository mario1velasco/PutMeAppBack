package com.putmeapp.restful.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service("userService")
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }

    public UserDTO findUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        return userMapper.userToUserDTO(user);
    }

    public List<UserDTO> getAllUsers() {
        return userMapper.userToUserDTOs(userRepository.findAll());
    }

    public UserDTO createUser(UserDTO userDTO) {
        User user = userMapper.userDTOToUser(userDTO);
        User saveUser = userRepository.save(user);
        return userMapper.userToUserDTO(saveUser);
    }

    public UserDTO updateUser(UserDTO userDTO, Long id) {
        User user = userMapper.userDTOToUser(userDTO);
        User updateUser = userRepository.findById(id).orElse(null);
        if (updateUser != null) {
            updateUser.setFirstName(user.getFirstName());
            updateUser.setLastName(user.getLastName());
            updateUser.setEmail(user.getEmail());
            try {
                final User userUpdate = userRepository.save(updateUser);
                return userMapper.userToUserDTO(userUpdate);
            } catch (Exception e) {
                // TODO: handle exception
                throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED, "User not update");
            }
        }
        return null;
    }

    public Boolean deleteUser(Long id) {
        User delUser = userRepository.findById(id).orElse(null);
        if (delUser != null) {
            userRepository.delete(delUser);
            return true;
        }
        return false;
    }
}