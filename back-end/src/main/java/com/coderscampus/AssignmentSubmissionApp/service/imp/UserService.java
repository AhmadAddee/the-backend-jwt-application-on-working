package com.coderscampus.AssignmentSubmissionApp.service.imp;

import com.coderscampus.AssignmentSubmissionApp.db.dbo.PostDb;
import com.coderscampus.AssignmentSubmissionApp.db.dbo.UserDb;
import com.coderscampus.AssignmentSubmissionApp.db.repositories.UserRepository;
import com.coderscampus.AssignmentSubmissionApp.dto.Post;
import com.coderscampus.AssignmentSubmissionApp.dto.User;
import com.coderscampus.AssignmentSubmissionApp.service.IUserService;
import com.coderscampus.AssignmentSubmissionApp.util.DateUtils;
import com.coderscampus.AssignmentSubmissionApp.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private JwtUtil jwtUtil;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getUsers() {
        Iterable<UserDb> userDbs = this.userRepository.findAll();
        List<User> userList = new ArrayList<>();
        userDbs.forEach(userDb -> userList.add(userDbToUser(userDb)));
        return userList;
    }

    @Override
    public User getUserByUsername(String jwt) {
        if (jwt == null)
            throw new RuntimeException("Post cannot be null");
        String username = jwtUtil.getUsernameFromToken(jwt.split(" ")[1].trim().replaceAll("^\"|\"$", ""));
        Optional<UserDb> userDb = this.userRepository.findByUsername(username);
        return userDb.get() != null? userDbToUser(userDb.get()) : null;
    }

    @Override
    public String addUser(User user) {
        System.out.println(user);
        if (user.getUsername() == null || user.getFullName() == null ||
            user.getAge() == 0 || user.getPassword() == null)
            return null;
        UserDb userDb = new UserDb();
        userDb.setUsername(user.getUsername());
        userDb.setFull_name(user.getFullName());
        userDb.setAge(user.getAge());
        userDb.setPassword(user.getPassword());
        this.userRepository.save(userDb);
        System.out.println("user added successfully");
        return user.getUsername();
    }

    @Override
    public User logIn(User user) {
        if (user == null)
            throw new RuntimeException("Post cannot be null");
        Optional<UserDb> userDb = this.userRepository.findByUsername(user.getUsername());
        return userDb == null? null : userDb.get().getPassword().equals(user.getPassword())? userDbToUser(userDb.get()) : null;
    }

    private User userDbToUser(UserDb userDb) {
        User user = new User();
        user.setUsername(userDb.getUsername());
        user.setFullName(userDb.getFull_name());
        user.setAge(userDb.getAge());
        user.setPassword(userDb.getPassword());
        user.setPostList(new ArrayList<>());
        return user;
    }

    private Post postDbToPost(PostDb postDb) {
        Post post =  new Post();
        post.setId(postDb.getId());
        post.setCreator(postDb.getCreator().getUsername());
        post.setContent(postDb.getContent());
        Date date = new Date(postDb.getCreatedDate().getTime());
        post.setCreatedDate(date);
        post.setTimeAgo(DateUtils.calculateTimeAgo(post.getCreatedDate()));
        return post;
    }
}
