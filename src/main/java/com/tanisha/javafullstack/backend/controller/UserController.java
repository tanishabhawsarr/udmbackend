package com.tanisha.javafullstack.backend.controller;


import com.tanisha.javafullstack.backend.exception.UserNotFoundException;
import com.tanisha.javafullstack.backend.model.User;
import com.tanisha.javafullstack.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/user")
    User newUser(@RequestBody User newUser) {
        return userRepository.save(newUser);
    }

    @GetMapping("/users")
    List<User> getallUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    User getUserById(@PathVariable Long id) {
        return userRepository.findById(id)
           .orElseThrow(()->new UserNotFoundException(id));
    }

    @PutMapping("/users/{id}")
        User updateUser(@RequestBody User newUser,@PathVariable Long id){
            return userRepository.findById(id)
                    .map(user->{
                        user.setUsername(newUser.getUsername());
                        user.setName(newUser.getName());
                        user.setEmail(newUser.getEmail());
                        return userRepository.save(user);
                    }).orElseThrow(()->new UserNotFoundException(id));
    }

    @DeleteMapping("/users/{id}")
       String deleteUser(@PathVariable Long id) {
        if(!userRepository.existsById(id)) {
            throw new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
        return "User with id " + id + " has been deleted successfully.";
    }

}
