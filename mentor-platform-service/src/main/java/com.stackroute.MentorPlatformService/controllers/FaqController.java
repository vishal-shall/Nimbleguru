package com.stackroute.MentorPlatformService.controllers;

import com.stackroute.MentorPlatformService.domain.Faq1;
import com.stackroute.MentorPlatformService.service.FaqServiceImplementation1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class FaqController
{
    private FaqServiceImplementation1 faqServiceImplementation;
    @Autowired
    public FaqController(FaqServiceImplementation1 faqServiceImplementation){this.faqServiceImplementation = faqServiceImplementation;}
    @PostMapping("/register")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Faq1> registerFaq(@RequestBody Faq1 faq)
    {
        Faq1 FaqObj = null;
        FaqObj = faqServiceImplementation.saveFAQ(faq);
        return new ResponseEntity<>(FaqObj, HttpStatus.OK);
    }
    @GetMapping("/faq/{subject}")
    @CrossOrigin(origins = "*")
    //   @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<List<Faq1>> getFaqBySubject(@PathVariable("subject") String subject){
        return new ResponseEntity<List<Faq1>>( (List<Faq1>) faqServiceImplementation.getFaqBySubject(subject) , HttpStatus.OK);
    }
//    @GetMapping("/faq/{subject}")
//    public ResponseEntity<>getFaq(){
//        return new ResponseEntity()
//    }
}
