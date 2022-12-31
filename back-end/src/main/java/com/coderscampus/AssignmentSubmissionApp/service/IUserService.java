package com.coderscampus.AssignmentSubmissionApp.service;

import com.coderscampus.AssignmentSubmissionApp.dto.User;

import java.util.List;

public interface IUserService {
    List<User> getUsers();
    User getUserByUsername(String jwt);
    String addUser(User user);
    User logIn(User user);
}
