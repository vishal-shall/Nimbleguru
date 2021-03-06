package com.stackroute.StudentProfileService.service;

import com.stackroute.StudentProfileService.domain.Session;
import com.stackroute.StudentProfileService.domain.Student;
import com.stackroute.StudentProfileService.exception.UserAlreadyExistException;

import java.util.List;

public interface StudentService {
    Student saveStudent(Student student) throws UserAlreadyExistException;
    Student getStudentByEmail(String email);
    List<Student> getAll();
//    Session getBySessionId(String sessionId);
}
