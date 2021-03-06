package com.stackroute.MentorProfileService.controller;
import com.stackroute.MentorProfileService.domain.Mentor;
import com.stackroute.MentorProfileService.domain.Session;
import com.stackroute.MentorProfileService.service.MentorServiceImplementation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping
@CrossOrigin(origins = "*")

public class MentorController {
    private MentorServiceImplementation mentorService;
    private static final String SENDING_URL = "/topic/server-broadcaster";
//    private static final String RECEIVING_URL = "/server-receiver";
private static final Logger logger = LoggerFactory.getLogger(MentorController.class);
    private final SimpMessagingTemplate template;

    private String message = "";

    @Autowired
    public MentorController(MentorServiceImplementation mentorService, SimpMessagingTemplate template) {
        this.mentorService = mentorService;
        this.template = template;
    }
//    public MentorController(SimpMessagingTemplate template) {
//        this.template = template;
//    }

    @GetMapping("/mentor")
 //   @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<List<Mentor>> getMentor(String email){
        return new ResponseEntity<List<Mentor>>( (List<Mentor>) mentorService.getAll() , HttpStatus.OK);
    }

    @PostMapping("/register")
    // @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Mentor> registermentor(@RequestBody Mentor mentor) throws Exception
    {

        if(mentor.getSessions() == null)
            mentor.setSessions(new ArrayList<>());

        String tempEmail = mentor.getEmail();
        if (tempEmail != null && tempEmail != "") {
            Mentor fetchmentor = mentorService.getMentorByEmail(tempEmail);
            if (fetchmentor != null) {
                throw new Exception("user with " + tempEmail + " is already exist");
            }
        }
        try {
            if(mentor.getSessions().equals(null)){
                mentor.setRating(4);
            }
            if(mentor.getSessions().size()==0){
                mentor.setRating(4);
            }

            mentor.setCreditStore(mentorService.calculateCredit(mentor.getJoining_date(), mentor.getRating(),mentor.getSessions().size()));
            logger.info(""+mentor.getCreditStore());
        }
        catch(ParseException e){
            logger.info(""+2);
        }
        Mentor mentorObj = mentorService.saveMentor(mentor);
        return new ResponseEntity<>(mentorObj, HttpStatus.OK);

    }

    @GetMapping("/mentor/{email}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Mentor> getByEmail (@PathVariable String email){
        return new ResponseEntity<Mentor>(mentorService.getMentorByEmail(email), HttpStatus.OK);
    }


    @GetMapping("/mentors/{subject}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<?> getBySubject (@PathVariable String subject) throws Exception {
        try {
            Mentor getall = mentorService.getMentorBySubject(subject);
            template.convertAndSend("/topic/server-broadcaster", buildNextMessage());
            return new ResponseEntity<Mentor>(getall, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<String>("Mentor Not Found",HttpStatus.OK);
        }
    }

    @PutMapping("/updateProfile")
//    @CrossOrigin(origins = "*")
    public ResponseEntity<Mentor> updateMentor(@RequestBody Mentor mentor){
        Mentor updatedMentor = mentorService.updateMentor(mentor);
        return new ResponseEntity<>(updatedMentor, HttpStatus.OK);
    }


    @PatchMapping("/mentors/{email}/feedback")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Mentor> setRating(@RequestParam Integer rating,@PathVariable String email){
        return new ResponseEntity<Mentor>(mentorService.saveRating(rating,email), HttpStatus.OK);

    }


    @GetMapping("/sessions/{email}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<List<Session>> getsessionByEmail(@PathVariable String email){
        
        return new ResponseEntity<List<Session>>(mentorService.getMentorByEmail(email).getSessions(), HttpStatus.OK);
    }

//        List<Mentor>Final =  new ArrayList<>();
//        Final.addAll(mentorService.getMentorBySubject(subject));
//        template.convertAndSend("/topic/server-broadcaster",buildNextMessage());
//        return new ResponseEntity<Mentor>(Final.get(0),HttpStatus.OK);
//    }

//    @MessageMapping(RECEIVING_URL)
//    public void onReceivedMessage(String message) {

//    }

    @SubscribeMapping(SENDING_URL)
    public String onSubscribe() {
        return message;
    }

//    @Scheduled(fixedRate = 100000)
//    @GetMapping("/message")
//    @GetMapping("/notification")

    public void sendMessage() {
        template.convertAndSend(buildNextMessage());
    }

    private String buildNextMessage() {
        message = "A new session is waiting for you";

        return message;
    }

}
