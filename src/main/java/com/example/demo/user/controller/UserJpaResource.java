package com.example.demo.user.controller;

import com.example.demo.user.entity.Post;
import com.example.demo.user.entity.User;
import com.example.demo.user.exception.UserNotFoundException;
import com.example.demo.user.repository.PostRepository;
import com.example.demo.user.repository.UserRepository;
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

    private UserRepository userRepository;

    private PostRepository postRepository;

    public UserJpaResource(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    //GET /users
    @GetMapping("/jpa/users")
    public List<User> retrieveAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/jpa/users/{id}")
    public EntityModel<User> findUserById(@PathVariable Integer id) {
        Optional<User> user = userRepository.findById(id);
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
        userRepository.deleteById(id);
    }

    @GetMapping("/jpa/users/{id}/post")
    public List<Post> listOfPostByUserId(@PathVariable int id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()){
            throw new UserNotFoundException("id not found :: " + id);
        }
        return user.get().getPostList();
    }

    @PostMapping("/jpa/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User save = userRepository.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(save.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PostMapping("/jpa/users/{id}/post")
    public ResponseEntity<Object> createPostForUser(@PathVariable int id, @Valid @RequestBody Post post) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException("Id not found :: " + id);
        }
        post.setUser(user.get());
        Post save = postRepository.save(post);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(save.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
}
