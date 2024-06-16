package com.ecommerce.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.user.Exception.UserException;
import com.ecommerce.user.models.User;
import com.ecommerce.user.request.LoginRequest;
import com.ecommerce.user.response.HeaderGenerator;
import com.ecommerce.user.services.UserService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping(value = "/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        if (!users.isEmpty()) {
            return new ResponseEntity<List<User>>(
                    users,
                    HttpStatus.OK);
        }
        return new ResponseEntity<List<User>>(
                HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/users", params = "email")
    public ResponseEntity<User> getUserByEmail(@RequestParam("email") String userEmail) throws UserException {
        User user = userService.getUserByEmail(userEmail);
        if (user != null) {
            return new ResponseEntity<User>(
                    user,
                    HttpStatus.OK);
        }
        return new ResponseEntity<User>(
                HttpStatus.NOT_FOUND);
    }

    @PostMapping("/signin")
    public ResponseEntity<String>loginUserHandler(@RequestBody LoginRequest loginRequest) throws UserException{

        String email = loginRequest.getEmail();

        String password = loginRequest.getPassword();

        User user = userService.getUserByEmail(email);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }

        if (!password.equals(user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }

        return ResponseEntity.ok("Sign-in successful");

    }

    @GetMapping(value = "/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) throws UserException {
        User user = userService.getUserById(id);
        if (user != null) {
            return new ResponseEntity<User>(
                    user,
                    HttpStatus.OK);
        }
        return new ResponseEntity<User>(
                HttpStatus.NOT_FOUND);
    }

    @PostMapping("/signup")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        if (user != null)
            try {
                User checkUser = userService.getUserByEmail(user.getEmail());
                if (checkUser != null) {
                    return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
                }

                userService.saveUser(user);
                return new ResponseEntity<User>(
                        user,
                        HttpStatus.CREATED);
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
    }

}
