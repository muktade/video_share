package com.example.myyoutube.controller;

import com.example.myyoutube.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import static com.example.myyoutube.common.Constants.KEY_MESSAGE;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;

    @GetMapping("/auth/login-form")
    public String loginPage(String message, String error, String logout, Model model) {
        if (error != null) {
            model.addAttribute("error", "Invalid username or password");
        }
        if (logout != null) {
            model.addAttribute("message", "You have been logged out successfully");
        }
        return "auth/login";
    }

    @PostMapping(value = {"/auth/login", "/auth/login/{message}"})
    public String loginsuccess(@PathVariable(value = "message", required = false) String message, Model model) {
        model.addAttribute(KEY_MESSAGE, message);
        return "index";
    }
}
