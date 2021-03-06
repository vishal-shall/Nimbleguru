package com.stackroute.MentorPlatformService.service;

import com.stackroute.MentorPlatformService.domain.Faq1;
import com.stackroute.MentorPlatformService.Repository.FAQRepo1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FaqServiceImplementation1 implements FaqService1 {
    private FAQRepo1 faqRepo;
    @Autowired
    public FaqServiceImplementation1(FAQRepo1 faqRepo){this.faqRepo = faqRepo;}

    @Override
    public Faq1 saveFAQ(Faq1 question) {
        return faqRepo.save(question);
    }

    @Override
    public List<Faq1> getFaqBySubject(String subject) {
        return (List<Faq1>)faqRepo.findBySubject(subject);

    };
}
