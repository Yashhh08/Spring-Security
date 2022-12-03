package com.masai.security;

import com.masai.exception.CustomException;
import com.masai.model.User;
import com.masai.repositery.userRepositery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private userRepositery userRepositery;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepositery.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("No user found with username : "+username));

        return new CustomUserDetails(user);


    }
}
