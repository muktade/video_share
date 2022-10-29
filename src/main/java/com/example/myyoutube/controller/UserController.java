package com.example.myyoutube.controller;


import com.example.myyoutube.entity.User;
import com.example.myyoutube.entity.Video;
import com.example.myyoutube.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Controller
@RequestMapping("/")
@AllArgsConstructor
public class UserController {

    private final UserService userService;


//    Authentication auth;
//
    @GetMapping("")
    public String showu(HttpSession session, Model model){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("auth", auth);
        Class<?> u=auth.getPrincipal().getClass();
        System.out.println(u.getSimpleName());
        System.out.println(session.getAttributeNames());

        return "index";
    }

    @GetMapping("dashboard")
    public String showDashoard(Model model){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("video", new Video());

        return "layout/dashboard";
    }

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
