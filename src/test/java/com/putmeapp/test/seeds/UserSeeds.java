package com.putmeapp.test.seeds;

import com.putmeapp.restful.user.User;
import com.putmeapp.restful.user.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserSeeds {
    @Autowired
    private UserRepository userRepository;

    public void createOneUser() {
        User user1 = createUser("Mario", "Velasco", "mario@altia.es", "asdasdf34$srg");
        userRepository.save(user1);
    }

    public void createManyUser() {
        User user1 = createUser("Mario", "Velasco", "mario@altia.es", "asdasdf34$srg");
        userRepository.save(user1);
        user1 = createUser("Pedro", "Velasco", "pedro@altia.es", "43dgfgdfgdfgdfg$srg");
        userRepository.save(user1);
        user1 = createUser("Irene", "Velasco", "irene@altia.es", "a54654645ghfhfgh");
        userRepository.save(user1);
        user1 = createUser("Gaspar", "Alonso", "gaspar@altia.es", "adfgdfDFGDFg545%&");
        userRepository.save(user1);
    }

    public User createUser(String firstName, String lastName, String email, String password) {
        User user = new User();
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPassword(password);
        return user;
    }
}
