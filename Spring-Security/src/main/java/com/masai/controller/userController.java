package com.masai.controller;

import com.masai.exception.CustomException;
import com.masai.model.DTO.UserDTO;
import com.masai.model.User;
import com.masai.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class userController {

    @Autowired
    private userService userService;

    @GetMapping("/")
    public String homePage(){
        return "Welcome to Spring Security Demo";
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@Valid @RequestBody User user) throws CustomException{

        User registeredUser = userService.registerUser(user);

        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);

    }

    @PatchMapping("/update/password")
    public ResponseEntity<User> updatePassword(@Valid @RequestBody UserDTO userDTO) throws CustomException{

        User updatedUser = userService.updatePassword(userDTO);

        return new ResponseEntity<>(updatedUser,HttpStatus.OK);

    }

    @DeleteMapping("/delete/{username}")
    public ResponseEntity<String> deleteUser(@PathVariable("username") String username) throws CustomException{

        String result = userService.deleteUser(username);

        return new ResponseEntity<>(result,HttpStatus.OK);

    }

    @GetMapping("/allUsers")
    public ResponseEntity<List<User>> getAllUsers() throws CustomException{

        List<User> users = userService.getAllUsers();

        return new ResponseEntity<>(users,HttpStatus.OK);

    }

    @GetMapping("/username/{username}")
    public ResponseEntity<User> userByUsername(@PathVariable("username") String username) throws CustomException{

        User user = userService.userByUsername(username);

        return new ResponseEntity<>(user, HttpStatus.OK);

    }

}
