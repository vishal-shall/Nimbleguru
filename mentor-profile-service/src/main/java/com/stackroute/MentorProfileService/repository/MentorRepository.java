package com.stackroute.MentorProfileService.repository;

import com.stackroute.MentorProfileService.domain.Mentor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MentorRepository extends MongoRepository<Mentor,Integer> {
    Mentor findByEmail(String email);
    List<Mentor> findBySubject(String subject);

}
