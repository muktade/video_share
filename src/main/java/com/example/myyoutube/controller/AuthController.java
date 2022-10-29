package com.example.myyoutube.controller;

import com.example.myyoutube.entity.User;
import com.example.myyoutube.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import static com.example.myyoutube.common.Constants.*;

@Controller
@RequestMapping("/auth/")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    Authentication auth = SecurityContextHolder.getContext().getAuthentication();


    @GetMapping(value = "register")
    public String registerForm(Model model) {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute(KEY_USER, new User());
        return "auth/register";
    }

    @PostMapping(value = "register")
    public String register(@Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "auth/register";
        }

        userService.saveUser(user);

        boolean isSaved = user.getId() != null;
        String msgKey, message, page;
        if (isSaved) {
            msgKey = KEY_MESSAGE;
            message = "Registration successful. You can login now.";
            page = "auth/login";
        } else {
            msgKey = KEY_ERROR;
            message = "Email already registered by another user";
            page = "auth/register";
            model.addAttribute(KEY_USER, user);
        }

        model.addAttribute(msgKey, message);
        return page;
    }

    @GetMapping("login")
    public String loginForm(String message, String error, String logout, Model model) {
        if (error != null) {
            model.addAttribute(KEY_ERROR, "Invalid username or password");
        }
        if (logout != null) {
            model.addAttribute(KEY_MESSAGE, "You have been logged out successfully");
        } else {
            model.addAttribute(KEY_MESSAGE, message);
        }

        return "auth/login";
    }

    @PostMapping(value = {"login", "login/{message}"}, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String loginSuccess(@PathVariable(value = KEY_MESSAGE, required = false) String message, Model model, HttpSession session) {
        model.addAttribute(KEY_MESSAGE, message);
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        session.setAttribute("user", user);
        return "redirect:/dashboard/";
    }
}
