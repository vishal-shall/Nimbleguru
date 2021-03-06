package com.stackroute.MentorProfileService.service;
import com.stackroute.MentorProfileService.domain.Mentor;

import java.text.ParseException;

import com.stackroute.MentorProfileService.domain.Mentor;
import java.util.List;

public interface MentorService {

    Mentor saveMentor(Mentor student);
    Mentor getMentorByEmail(String email);
    List<Mentor> getAll();
    Mentor getMentorBySubject(String subject) throws Exception;
    Mentor updateMentor(Mentor mentor);
    float calculateCredit(String join_date, float avg_rating, int no_sessions) throws ParseException;
    Mentor saveRating(Integer rating,String email);

}
