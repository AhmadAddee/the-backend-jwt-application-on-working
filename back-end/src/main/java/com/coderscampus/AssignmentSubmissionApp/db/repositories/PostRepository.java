package com.coderscampus.AssignmentSubmissionApp.db.repositories;

import com.coderscampus.AssignmentSubmissionApp.db.dbo.PostDb;
import com.coderscampus.AssignmentSubmissionApp.db.dbo.UserDb;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends CrudRepository<PostDb, Integer> {
    Iterable<PostDb> findPostByCreator(UserDb creator);
    PostDb findById(int id);
}
