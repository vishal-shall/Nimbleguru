package com.stackroute.MentorPlatformService.Repository;

import com.stackroute.MentorPlatformService.domain.Faq1;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FAQRepo1 extends MongoRepository<Faq1 ,Integer> {
    List<Faq1> findBySubject(String subject);
}
