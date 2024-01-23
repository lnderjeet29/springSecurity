package com.example.springSecurity.services.impl;

import com.example.springSecurity.Repo.UserRepository;
import com.example.springSecurity.entity.User;
import com.example.springSecurity.exception.BadApiRequest;
import com.example.springSecurity.services.UserServices;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServicesImp implements UserServices {

    private final UserRepository userRepository;
    public UserDetailsService userDetailsService(){
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return userRepository.findByEmail(username)
                        .orElseThrow(()->new UsernameNotFoundException("user not found..."));
            }
        };
    }
    public User findUser(String username){
        return userRepository.findByEmail(username)
                .orElseThrow(()->new BadApiRequest("user not found..."));
    }
}
