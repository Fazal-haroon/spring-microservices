package com.example.demo.user.controller;

import com.example.demo.user.entity.Post;
import com.example.demo.user.entity.User;
import com.example.demo.user.exception.UserNotFoundException;
import com.example.demo.user.repository.UserRepository;
import com.example.demo.user.service.UserDaoService;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserJpaResource {

    private UserRepository userDaoService;

    public UserJpaResource(UserRepository userDaoService) {
        this.userDaoService = userDaoService;
    }

    //GET /users
    @GetMapping("/jpa/users")
    public List<User> retrieveAllUsers(){
        return userDaoService.findAll();
    }

    @GetMapping("/jpa/users/{id}")
    public EntityModel<User> findUserById(@PathVariable Integer id) {
        Optional<User> user = userDaoService.findById(id);
        if(user.isEmpty()){
            throw new UserNotFoundException("id not found :: " + id);
        }
        EntityModel<User> entityModel = EntityModel.of(user.get());
        WebMvcLinkBuilder linkBuilder = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        entityModel.add(linkBuilder.withRel("all-users"));
        return entityModel;
    }

    @DeleteMapping("/jpa/users/{id}")
    public void deleteUserById(@PathVariable int id) {
        userDaoService.deleteById(id);
    }

    @GetMapping("/jpa/users/{id}/post")
    public List<Post> listOfPostByUserId(@PathVariable int id) {
        Optional<User> user = userDaoService.findById(id);
        if(user.isEmpty()){
            throw new UserNotFoundException("id not found :: " + id);
        }
        return user.get().getPostList();
    }

    @PostMapping("/jpa/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User save = userDaoService.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(save.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
}
