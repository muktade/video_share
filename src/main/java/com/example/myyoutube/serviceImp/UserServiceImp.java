package com.example.myyoutube.serviceImp;


import com.example.myyoutube.entity.User;
import com.example.myyoutube.repository.UserRepository;
import com.example.myyoutube.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public User saveUser(User user) {

        User usr = new User();
        usr.setName(user.getName());
        usr.setEmail(user.getEmail());
        usr.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(usr); ///for user save
    }

    @Override
    public List<User> getUser(Long[] ids) {
        return userRepository.findAllById(Arrays.asList(ids));
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)   ///ekhane
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found"));
    }
}
