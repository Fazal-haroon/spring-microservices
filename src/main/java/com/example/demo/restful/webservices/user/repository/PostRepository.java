package com.example.demo.restful.webservices.user.repository;

import com.example.demo.restful.webservices.user.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
}
