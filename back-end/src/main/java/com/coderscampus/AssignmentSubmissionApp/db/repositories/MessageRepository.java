
package com.coderscampus.AssignmentSubmissionApp.db.repositories;

import com.coderscampus.AssignmentSubmissionApp.db.dbo.MessageDb;
import com.coderscampus.AssignmentSubmissionApp.db.dbo.UserDb;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends CrudRepository<MessageDb, Integer> {
    Iterable<MessageDb> findMessageByReceiver(UserDb receiver);
    MessageDb findById(Long id);
}
