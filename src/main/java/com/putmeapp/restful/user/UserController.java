package com.putmeapp.restful.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/hello")
    public String helloString() {
        String string = "Hello, World";
        return string;
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> all() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDTO> one(@Valid @PathVariable(value = "id") final Long id) {
        return ResponseEntity.ok(userService.findUserById(id));
    }

    @PostMapping("/users")
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody final UserDTO userDTO) {
        return ResponseEntity.ok(userService.createUser(userDTO));
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody final UserDTO userDTO,
            @PathVariable(value = "id") final Long id) {
        return ResponseEntity.ok(userService.updateUser(userDTO, id));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Map<String, String>> deleteUser(@PathVariable final Long id) {
        final Map<String, String> response = new HashMap<String, String>();
        if (userService.deleteUser(id)) {
            response.put("status", "success");
            response.put("message", "user deleted successfully");
            return ResponseEntity.ok(response);
        } else {
            response.put("status", "error");
            response.put("message", "Somthing went wrong when delete the user");
            return ResponseEntity.status(500).body(response);
        }
    }
}