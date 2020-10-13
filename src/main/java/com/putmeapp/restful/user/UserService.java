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

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(User user, Long id) {
        User updateUser = userRepository.findById(id).orElse(null);
        if (updateUser != null) {
            updateUser.setFirstName(user.getFirstName());
            updateUser.setLastName(user.getLastName());
        }
        final User myuser = userRepository.save(updateUser);
        return myuser;
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