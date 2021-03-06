package com.stackroute.StudentProfileService.controller;

import com.stackroute.StudentProfileService.domain.AuthStudent;
import com.stackroute.StudentProfileService.domain.Session;
import com.stackroute.StudentProfileService.domain.Student;
import com.stackroute.StudentProfileService.exception.UserAlreadyExistException;
import com.stackroute.StudentProfileService.service.RabbitMqSender;
import com.stackroute.StudentProfileService.service.StudentService;
import com.stackroute.StudentProfileService.service.StudentServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping

public class StudentController {
    private StudentServiceImplementation studentService;

    private RabbitMqSender rabbitMqSender;


    @Value("${app.message}")
    private String message;
    @Value("${spring.rabbitmq.routingkey}")
    private String routingkey;

    @Autowired
    public StudentController(RabbitMqSender rabbitMqSender, StudentServiceImplementation studentService) {
        this.studentService = studentService;
        this.rabbitMqSender = rabbitMqSender;
    }
    @GetMapping("/students")
    @CrossOrigin(origins = "*")
    public ResponseEntity<List<Student>> getStudents(String email){
        return new ResponseEntity<List<Student>>( (List<Student>) studentService.getAll() , HttpStatus.OK);
    }

    @PostMapping("/registration")
    @CrossOrigin(origins = "*")
    public ResponseEntity<?> registerStudent(@RequestBody Student student) throws UserAlreadyExistException {
        try {
            Student studentObj = studentService.saveStudent(student);
            AuthStudent authStudent = new AuthStudent();
            authStudent.setEmail(student.getEmail());
            authStudent.setPassword(student.getPassword());
            rabbitMqSender.send(routingkey,authStudent);
            return new ResponseEntity<>(studentObj, HttpStatus.OK);
        }catch (UserAlreadyExistException exception){
            return new ResponseEntity<String>("User With this email already Exist",HttpStatus.OK);
        }
    }

    @GetMapping("/students/{email}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Student> getByEmail (@PathVariable String email){
        return new ResponseEntity<Student>(studentService.getStudentByEmail(email), HttpStatus.OK);
    }
    @GetMapping("/sessions/{email}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<List<Session>> getsessionByEmail(@PathVariable String email){
        return new ResponseEntity<List<Session>>(studentService.getStudentByEmail(email).getSessions(), HttpStatus.OK);
    }
//    @PostMapping("/sessions/{sessionId}/{subject}/{query}")
//    @CrossOrigin(origins = "*")
//    public ResponseEntity<Session> getUpdatedSession(@PathVariable String sessionId,@PathVariable String subject,@PathVariable String email){
//
//    }
//    @GetMapping("/sessions/{sessionId}")
//    @CrossOrigin(origins = "*")
//    public ResponseEntity<Session> getBySessionId (@PathVariable String sessionId){
//        return new ResponseEntity<Session>(studentService.getBySessionId(sessionId), HttpStatus.OK);
//    }
}
