package com.coderscampus.AssignmentSubmissionApp.service.imp;

import com.coderscampus.AssignmentSubmissionApp.db.dbo.PostDb;
import com.coderscampus.AssignmentSubmissionApp.db.dbo.UserDb;
import com.coderscampus.AssignmentSubmissionApp.db.repositories.PostRepository;
import com.coderscampus.AssignmentSubmissionApp.db.repositories.UserRepository;
import com.coderscampus.AssignmentSubmissionApp.dto.Post;
import com.coderscampus.AssignmentSubmissionApp.service.IPostService;
import com.coderscampus.AssignmentSubmissionApp.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PostService implements IPostService {
    @Autowired
    private final PostRepository postRepository;
    @Autowired
    private final UserRepository userRepository;

    protected PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Post> getPosts(){
        Iterable<PostDb> postDbs = this.postRepository.findAll();
        List<Post> postList = new ArrayList<>();
        postDbs.forEach(postDb -> postList.add(postDbToPost(postDb)));
        return postList;
    }

    @Override
    public List<Post> getPostByCreator(String creator){
        Optional<UserDb> userDb = this.userRepository.findByUsername(creator);
        if (userDb == null) return null;
        Iterable<PostDb> postDbs = this.postRepository.findPostByCreator(userDb.get());
        List<Post> postList = new ArrayList<>();
        postDbs.forEach(postDb -> postList.add(postDbToPost(postDb)));
        return postList;
    }

    @Override
    public String createPost(Post post) {
        if (post.getCreator() == null || post.getContent() == null)
            return "Post cannot be null!";
        Optional<UserDb> userDb = this.userRepository.findByUsername(post.getCreator());
        if (userDb == null)
            return "The creator of the post cannot be null!";
        PostDb postDb = postToPostDB(post);
        postDb.setCreator(userDb.get());

        this.postRepository.save(postDb);
        return "Created successfully!";
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

    private PostDb postToPostDB(Post post) {
        Date now = new Date();
        Timestamp timestamp = new Timestamp(now.getTime());

        PostDb postDb = new PostDb();
        postDb.setContent(post.getContent());
        postDb.setCreatedDate(timestamp);
        return postDb;
    }
}
