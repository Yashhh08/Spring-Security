package com.masai.service;

import com.masai.exception.CustomException;
import com.masai.model.DTO.UserDTO;
import com.masai.model.User;
import com.masai.repositery.userRepositery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements userService{

    @Autowired
    private userRepositery userRepositery;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public User registerUser(User user) throws CustomException {

        Optional<User> exist = userRepositery.findByUsername(user.getUsername());

        if(exist.isPresent())
        {
            throw new CustomException("User already Exist with username : "+user.getUsername());
        }
        else
        {
            user.setRole("ROLE_"+user.getRole());
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepositery.save(user);
        }

    }

    @Override
    public User updatePassword(UserDTO userDTO) throws CustomException {

        User user1 = userRepositery.findByUsername(userDTO.getUsername()).orElseThrow(()-> new CustomException("user not found with username : "+userDTO.getUsername()));

        if(passwordEncoder.matches(userDTO.getPassword(), user1.getPassword()))
        {
            user1.setPassword(passwordEncoder.encode(userDTO.getNewPassword()));
            return userRepositery.save(user1);
        }
        else
        {
            throw new CustomException("wrong password");
        }

    }

    @Override
    public String deleteUser(String username) throws CustomException {

        User user = userRepositery.findByUsername(username).orElseThrow(()->new CustomException("User not found with username : "+username));

        userRepositery.delete(user);

        return " user with username : "+username+" deleted succseefully";

    }

    @Override
    public List<User> getAllUsers() throws CustomException {

        List<User> users = userRepositery.findAll();

        if(users.isEmpty())
        {
            throw new CustomException("No user found");
        }
        else
        {
            return users;
        }

    }

    @Override
    public User userByUsername(String username) throws CustomException {

        User user = userRepositery.findByUsername(username).orElseThrow(()->new CustomException("User not found with username : "+username));

        return user;

    }
}
