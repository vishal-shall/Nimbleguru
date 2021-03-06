package com.stackroute.StudentProfileService.service;

import com.stackroute.StudentProfileService.domain.Session;
import com.stackroute.StudentProfileService.domain.Student;
import com.stackroute.StudentProfileService.exception.UserAlreadyExistException;
import com.stackroute.StudentProfileService.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class StudentServiceImplementation implements StudentService {

    private StudentRepository studentRepository;
    @Autowired
    public StudentServiceImplementation(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    @Override
    public Student saveStudent(Student student) throws UserAlreadyExistException {
        Optional<Student> student1 = studentRepository.findByEmail(student.getEmail());
        if (student1.isPresent())
            throw new UserAlreadyExistException("User With this emial already Exist");
        return studentRepository.save(student);
    }

    @Override
    public Student getStudentByEmail(String email) {

        Student m1 = studentRepository.findByEmail(email).get();
        ;
        List<Session> s1 = m1.getSessions();
        System.out.println(s1);
        if ( s1!=null)  {
            List<Session> sortedList = s1.stream()
                    .sorted(Comparator.comparing(Session::getStartTime).reversed())
                    .collect(Collectors.toList());
            m1.setSessions(sortedList);}

            return m1;
        }

    @Override
    public List<Student> getAll() {
        return studentRepository.findAll();
    }

//    @Override
//    public Session getBySessionId(String sessionId) {
//        return studentRepository.findBySessionId(sessionId);
//    }
}
