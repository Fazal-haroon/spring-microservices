package com.example.demo.restful.webservices.user.repository;

import com.example.demo.restful.webservices.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
