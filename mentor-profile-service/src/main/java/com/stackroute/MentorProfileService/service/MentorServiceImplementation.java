package com.stackroute.MentorProfileService.service;
import com.stackroute.MentorProfileService.domain.Mentor;
import com.stackroute.MentorProfileService.domain.Session;
import com.stackroute.MentorProfileService.domain.TimeSlot;
import com.stackroute.MentorProfileService.repository.MentorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;
import java.util.List;
import java.util.Optional;

@Service
public class MentorServiceImplementation implements MentorService {

    private MentorRepository mentorRepository;
    private static final Logger logger = LoggerFactory.getLogger(MentorServiceImplementation.class);

    @Autowired
    public MentorServiceImplementation(MentorRepository mentorRepository) {
        this.mentorRepository = mentorRepository;
    }


    @Override
    public Mentor saveMentor(Mentor mentor) {
        return mentorRepository.save(mentor);
    }

    @Override
    public Mentor getMentorByEmail(String email) {
        Mentor m1 = mentorRepository.findByEmail(email);
        if(m1==null){
            return m1;
        }
        List<Session> s1 = m1.getSessions();
        List<Session> s2 = new ArrayList<>(s1);
//        s2.sort(
//                Comparator
//                        .comparing( ( Session s ) -> s.getStartTime())
//                        .reversed()
//                        .thenComparing(
//                                Comparator
//                                        .comparing( ( Session s ) -> LocalTime.parse(s.getStartTime()))
//                        )
//        );
//        m1.setSessions(s2);
        if ( s1!=null)
            { List<Session> sortedList = s1.stream()
                .sorted(Comparator.comparing(Session :: getStartTime).reversed())
                .collect(Collectors.toList());
        m1.setSessions(sortedList);}
        return m1;
    }

    @Override
    public List<Mentor> getAll() {
        return mentorRepository.findAll();
    }

    @Override
    public Mentor getMentorBySubject(String subject) throws Exception {
        try {
            LocalTime currentTime = LocalTime.now();
            int gethours = currentTime.getHour();
//        TimeSlot timeSlot = new TimeSlot();
            List<Mentor> m = mentorRepository.findBySubject(subject);
            logger.info("Mentor list retrieved :: " + m.get(0));
            logger.info("GET Hours :: " + gethours);
            List<Mentor> m1 = new ArrayList<>();
            for (Mentor mentor : m) {
                List<TimeSlot> ts = mentor.getTime();
                for (TimeSlot time : ts) {
                    if (time.getStartTime() <= gethours && time.getEndTime() >= gethours) {
                        m1.add(mentor);
                    }
                }
            }
//        Mentor maxValue = m1.stream()
//                .filter((mentor) -> mentor.getStatus().equals("Active"))
//                .max(Comparator.comparing(v -> v.getCreditStore())).get();
            Mentor maxValue = m1.stream().max(Comparator.comparing(v -> v.getCreditStore())).get();
            return maxValue;
        } catch (Exception e) {
            throw new Exception("Mentor Not Found");
        }
    }


    @Override
    public Mentor updateMentor(Mentor mentor) {
        Mentor updatedMentor = null;
//        mentorRepository.findByEmail(mentor.getEmail());
        Mentor getMentor = mentorRepository.findByEmail(mentor.getEmail());
        getMentor.setName(mentor.getName());
        getMentor.setPhoneNumber(mentor.getPhoneNumber());
        getMentor.setQualification(mentor.getQualification());
        getMentor.setTime(mentor.getTime());
        getMentor.setSubject(mentor.getSubject());
        updatedMentor = saveMentor(getMentor);
        return updatedMentor;
    }

    @Override
    public float calculateCredit(String s, float avg_rating, int no_sessions) throws ParseException {
        //String s = "1994/06/23";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Date d = sdf.parse(s);
        Calendar c = Calendar.getInstance();
        c.setTime(d);

        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        int date = c.get(Calendar.DATE);
        LocalDate l1 = LocalDate.of(year, month, date);
        LocalDate now1 = LocalDate.now();
        Period diff1 = Period.between(l1, now1);
        float res_credit = 30;
        res_credit = (res_credit + diff1.getYears() * 3 + avg_rating * 10 - no_sessions * 6) / (diff1.getYears() + avg_rating + no_sessions);
        String x = df.format(res_credit);
        return Float.parseFloat(x);
    }


    private static DecimalFormat df = new DecimalFormat("0.00");
//    public List<Mentor> getMentorBySubject(String subject) {
//        Mentor mentor1 = new Mentor(1 , "rajesh@gmail.com","xxxxxxxxx", "Rajesh", 4, "comment", "B.tech", "Maths","2-2:15",null);
//        Mentor mentor2 = new Mentor(2, "suresh@gmail.com","xxxxxxxxx", "Suresh", 5, "comment", "B.tech", "Physics","2:15-2:30",null);
//        Mentor mentor3 = new Mentor(3, "kuresh@gmail.com","xxxxxxxxx", "kuresh", 3, "comment", "B.tech", "English","2:30-2:45",null);
//        mentorRepository.save(mentor1);
//        mentorRepository.save(mentor2);
//        mentorRepository.save(mentor3);
//
//        return mentorRepository.findBySubject(subject);
//    }


    @Override
    public Mentor saveRating(Integer rating , String email) {
        Mentor mentor = mentorRepository.findByEmail(email);
        int NoOfSessions = mentor.getSessions().size();
        if(NoOfSessions>0) {
            mentor.setRating((rating + mentor.getRating()) / (NoOfSessions+1));
        }
        return mentorRepository.save(mentor);


    }


//    @Override
//    public Mentor updateMentor(Mentor mentor)  {
//        Mentor updatedMentor = null;
//        mentorRepository.findByEmail(mentor.getEmail());
//        Mentor getMentor = mentorRepository.findByEmail(mentor.getEmail());
//        getMentor.setName(mentor.getName());
//        getMentor.setPhoneNumber(mentor.getPhoneNumber());
//        getMentor.setQualification(mentor.getQualification());
//        getMentor.setTime(mentor.getTime());
//        getMentor.setSubject(mentor.getSubject());
//        updatedMentor = saveMentor(getMentor);
//        return updatedMentor;
//    }
}
