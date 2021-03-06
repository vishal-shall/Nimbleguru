package com.stackroute.MentorPlatformService.Repository;

import com.stackroute.MentorPlatformService.DAO.Message;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends MongoRepository<Message, String> {
}
