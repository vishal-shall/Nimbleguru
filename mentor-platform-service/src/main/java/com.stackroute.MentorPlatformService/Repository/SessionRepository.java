package com.stackroute.MentorPlatformService.Repository;


import com.stackroute.MentorPlatformService.DAO.Session;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessionRepository  extends MongoRepository<Session, String> {
    void insert(String messageId);
    Session findBySessionId(String sessionID);
    Session findByTeacherEmailAndSessionStatus(String teacherEmail,String sessionStatus);
    List<Session> findByStudentEmail(String email);
    List<Session> findByTeacherEmail(String email);
}
