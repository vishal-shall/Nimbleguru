package com.stackroute.StudentProfileService.repository;

import com.stackroute.StudentProfileService.domain.Session;
import com.stackroute.StudentProfileService.domain.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends MongoRepository<Student,Integer> {
    Optional<Student> findByEmail(String email);
//    Session findBySessionId(String sessionId);
}
