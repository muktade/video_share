package com.example.myyoutube.controller;


import com.example.myyoutube.entity.User;
import com.example.myyoutube.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Controller
@RequestMapping("/user/")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    ////List of User
    @GetMapping("list/{id}") ///video id eta , ai id diye user id get korte hobe, pore ta diye user info ber korte hobe
    public List<User> getUsers(@PathVariable("id") Long id) {
        Long[] ids = new Long[2];   ///user id array diye user list arry ber korte hobe
        ids[0] = id;
        ids[1] = Long.parseLong("2");
        List<User> users = userService.getUser(ids);
        return users;
    }

}
