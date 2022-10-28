package com.example.myyoutube.service;

import com.example.myyoutube.entity.User;
//import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService extends UserDetailsService {

    ///save User
    public User saveUser(User user);

    ///Show User
    List<User> getUser(Long[] id);


}
