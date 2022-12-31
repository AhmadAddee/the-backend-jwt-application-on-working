package com.coderscampus.AssignmentSubmissionApp.controllers;

import com.coderscampus.AssignmentSubmissionApp.dto.Post;
import com.coderscampus.AssignmentSubmissionApp.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
//@CrossOrigin(origins = "http://localhost:3000")
@CrossOrigin(origins = "*", maxAge = 3600)
public class PostController {
    @Autowired
    private final IPostService postService;

    public PostController(IPostService postService) {
        this.postService = postService;
    }

    //@RequestMapping(path = "/posts", method = RequestMethod.GET)
    @GetMapping("/get-all")
    public List<Post> getPosts() {
        System.out.println("in post controller");
        return this.postService.getPosts();
    }

    @GetMapping("/get-posts")
    public List<Post> getPostByUsername(@RequestParam(value = "creator", required = false)String username) {
        return this.postService.getPostByCreator(username);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public String createPost(@RequestBody Post post) {
        System.out.println("Creating post, from controller: " + post);
        return this.postService.createPost(post);
    }

}
