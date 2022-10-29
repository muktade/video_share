package com.example.myyoutube.serviceImp;


import com.example.myyoutube.entity.User;
import com.example.myyoutube.repository.UserRepository;
import com.example.myyoutube.service.UserService;
import lombok.RequiredArgsConstructor;
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
    private final PasswordEncoder passwordEncoder;


    @Override
    public User saveUser(User user) {
        boolean alreadyExists = userRepository.existsByUsername(user.getUsername());
        if (!alreadyExists) {
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
            userRepository.save(user);
        }
        return user;
    }

    @Override
    public List<User> getUser(Long[] ids) {
        return userRepository.findAllById(Arrays.asList(ids));
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found"));
    }
}
