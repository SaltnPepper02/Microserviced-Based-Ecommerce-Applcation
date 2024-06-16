package com.ecommerce.user.services;

import java.util.List;

import com.ecommerce.user.Exception.UserException;
import com.ecommerce.user.models.User;


public interface UserService {

    List<User> getAllUsers();
    User getUserById(Long id) throws UserException;
    User getUserByEmail(String userEmail) throws UserException;
    User saveUser(User user);
    
}
