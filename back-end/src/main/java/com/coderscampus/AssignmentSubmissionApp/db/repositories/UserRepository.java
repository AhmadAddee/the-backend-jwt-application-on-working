package com.coderscampus.AssignmentSubmissionApp.db.repositories;

import com.coderscampus.AssignmentSubmissionApp.db.dbo.UserDb;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserDb, String> {
    Optional<UserDb> findByUsername(String username);
}
