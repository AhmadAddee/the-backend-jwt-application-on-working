package com.coderscampus.AssignmentSubmissionApp.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coderscampus.AssignmentSubmissionApp.db.dbo.Authorities;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository<Authorities, Long>{

}
