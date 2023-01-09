package com.coderscampus.AssignmentSubmissionApp.controllers;

import com.coderscampus.AssignmentSubmissionApp.dto.User;
import com.coderscampus.AssignmentSubmissionApp.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:3000")
public class UserContoller {

    @Autowired
    private final IUserService iUserService;

    public UserContoller(IUserService postService) {
        this.iUserService = postService;
    }

    @GetMapping("/get-all")
    public List<User> getUsers() {
        return this.iUserService.getUsers();
    }

    @GetMapping("/get-user")
    public User getUserByUsername(@RequestHeader(HttpHeaders.AUTHORIZATION) String jwt) {
        System.out.println("In user controller username is " + jwt);
        return this.iUserService.getUserByUsername(jwt);
    }

    @Deprecated
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public String AddUser(@RequestBody User user) {
        return this.iUserService.addUser(user);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.CREATED)
    public User Login(@RequestBody User user) {
        return this.iUserService.logIn(user);
    }

    @GetMapping("/home")
    public String home(Principal principal) {
        return "Hello, " + principal.getName() + " " + principal.toString();
    }

}
