package com.masai.service;

import com.masai.exception.CustomException;
import com.masai.model.DTO.UserDTO;
import com.masai.model.User;

import java.util.List;

public interface userService {

    public User registerUser(User user) throws CustomException;

    public User updatePassword(UserDTO userDTO) throws CustomException;

    public String deleteUser(String username) throws CustomException;

    public List<User> getAllUsers() throws CustomException;

    public User userByUsername(String username) throws CustomException;

}
