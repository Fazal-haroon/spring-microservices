package com.example.demo.restful.webservices.user.service;

import com.example.demo.restful.webservices.user.entity.User;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component
public class UserDaoService {
    private static List<User> userList = new ArrayList<>();
    private static Integer usersCount = 0;

    static {
        userList.add(new User(++usersCount, "fazal", LocalDate.now().minusYears(30)));
        userList.add(new User(++usersCount, "haroon", LocalDate.now().minusYears(25)));
        userList.add(new User(++usersCount, "Aleem", LocalDate.now().minusYears(50)));
    }

    public List<User> findAll(){
        return userList;
    }
    
    public User findOne(int id) {
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        return userList.stream().filter(predicate).findFirst().orElse(null);
    }

    public User save(User user){
        user.setId(++usersCount);
        userList.add(user);
        return user;
    }

    public void deleteOne(int id) {
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        userList.removeIf(predicate);
    }
}
