package com.stackroute.MentorPlatformService.service;
import com.stackroute.MentorPlatformService.domain.Faq1;
import java.util.List;

public interface FaqService1
{
    Faq1 saveFAQ(Faq1 question);
    List<Faq1> getFaqBySubject(String subject);

}

