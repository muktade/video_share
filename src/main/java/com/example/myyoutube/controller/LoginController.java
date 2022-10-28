package com.example.myyoutube.controller;

import com.example.myyoutube.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.Collection;

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
//		List<Role> roles = new ArrayList<Role>(){{add(new Role("ROLE_ADMIN"));}};
        return "auth/login";
    }

    @PostMapping(value = {"/auth/login", "/auth/login/{message}"})
    public String loginsuccess(@PathVariable(value = "message", required = false) String message, Model model, HttpSession session) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
//        String page = "";
        String page = "index";
//        String roleAdmin = "ROLE_ADMIN";
//        String roleUser = "ROLE_USER";
//        for (GrantedAuthority authority : authorities) {
//            if (authority.getAuthority().equals(roleAdmin)) {
//                page = "redirect:/user/list";
//            } else if (authority.getAuthority().equals(roleUser)) {
//                page = "redirect:/user/user-form";
//            } else {
//                page = "redirect:/logout";
//            }
//        }
        return page;
    }
}
