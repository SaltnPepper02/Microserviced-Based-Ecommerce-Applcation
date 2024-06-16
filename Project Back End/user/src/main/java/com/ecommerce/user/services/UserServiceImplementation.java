package com.ecommerce.user.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.user.Exception.UserException;
import com.ecommerce.user.models.User;
import com.ecommerce.user.models.UserRole;
import com.ecommerce.user.repository.UserRepository;
import com.ecommerce.user.repository.UserRoleRepository;

@Service
public class UserServiceImplementation implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    public UserServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) throws UserException {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            return user.get();
        }

        throw new UserException("User not found with id");
    }

    @Override
    public User getUserByEmail(String userEmail) throws UserException {
        User user = userRepository.findByEmail(userEmail);

        if(user == null){
            throw new UserException("User is not found with email");
        }
        return user;
    }

    @Override
    public User saveUser(User user) {
        user.setActive(1);
        UserRole role = userRoleRepository.findUserRoleByRoleName("ROLE_USER");
        user.setRole(role);
        return userRepository.save(user);
    }
}
