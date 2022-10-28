package com.example.myyoutube.controller;


import com.example.myyoutube.entity.User;
import com.example.myyoutube.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user/")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    ///For User Registration
    @GetMapping("user-form")
    public String addUserPage(Model model) {
        model.addAttribute("user", new User());
        return "user/add_user";
    }

    @PostMapping("save")
    public String  addUser(@RequestBody User user){
        userService.saveUser(user);
        return "success";
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
