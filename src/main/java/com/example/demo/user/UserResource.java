package com.example.demo.user;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserResource {

    private UserDaoService userDaoService;

    public UserResource(UserDaoService userDaoService) {
        this.userDaoService = userDaoService;
    }

    //GET /users
    @GetMapping("/users")
    public List<User> retrieveAllUsers(){
        return userDaoService.findAll();
    }

    @GetMapping("/users/{id}")
    public User findUser(@PathVariable int id) {
        return userDaoService.findOne(id);
    }

    @PostMapping("/users")
    public void createUser(@RequestBody User user) {
        userDaoService.save(user);
    }
}
