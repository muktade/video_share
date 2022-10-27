package com.example.myyoutube.controller;


import com.example.myyoutube.entity.User;
import com.example.myyoutube.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    ///For User Registration
    @PostMapping("save")
    public User addUser(@RequestBody User user){
        return userService.saveUser(user);
    }

    ////List of User
    @GetMapping("list/{id}")
    public List<User> getUsers(@PathVariable("id") Long id){
        Long[] ids = new Long[2];
        ids[0] = id;
        ids[1]= Long.parseLong("2");
        List<User> users = userService.getUser(ids);
        return users;
    }
}
