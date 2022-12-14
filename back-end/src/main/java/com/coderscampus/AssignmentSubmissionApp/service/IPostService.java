package com.coderscampus.AssignmentSubmissionApp.service;

import com.coderscampus.AssignmentSubmissionApp.dto.Post;

import java.util.List;

public interface IPostService {
    List<Post> getPosts();
    String createPost(Post post);
    List<Post> getPostByCreator(String username);
}
