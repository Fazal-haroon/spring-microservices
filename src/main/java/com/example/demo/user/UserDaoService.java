package com.example.demo.user;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component
public class UserDaoService {
    private static List<User> userList = new ArrayList<>();

    static {
        userList.add(new User(1, "fazal", LocalDate.now().minusYears(30)));
        userList.add(new User(2, "haroon", LocalDate.now().minusYears(25)));
        userList.add(new User(3, "Aleem", LocalDate.now().minusYears(50)));
    }

    public List<User> findAll(){
        return userList;
    }
    
    public User findOne(int id) {
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        return userList.stream().filter(predicate).findFirst().get();
    }
}