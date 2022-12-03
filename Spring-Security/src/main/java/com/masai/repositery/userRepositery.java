package com.masai.repositery;

import com.masai.exception.CustomException;
import com.masai.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface userRepositery extends JpaRepository<User,Integer> {


    Optional<User> findByUsername(String username);
}
