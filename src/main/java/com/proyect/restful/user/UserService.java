package com.proyect.restful.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service("userService")
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
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