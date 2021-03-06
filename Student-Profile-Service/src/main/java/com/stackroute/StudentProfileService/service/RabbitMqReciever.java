package com.stackroute.StudentProfileService.service;

import com.stackroute.StudentProfileService.domain.Session;
import com.stackroute.StudentProfileService.domain.Student;
import com.stackroute.StudentProfileService.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RabbitMqReciever implements RabbitListenerConfigurer {
    private StudentRepository studentRepository;
    private static final Logger logger = LoggerFactory.getLogger(RabbitMqReciever.class);


    public RabbitMqReciever(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @RabbitListener(queues = "${spring.rabbitmq.sessionqueue}")
    public void recieveMessage(Session session) {
        logger.info("Recieved programmer details" + session.toString());

        Student student = studentRepository.findByEmail(session.getStudentEmail()).get();
//        List<Session> list =
//                student.getSessions().add(session);
        if(student.getSessions()==null){
            List<Session> sessions = new ArrayList<>();
            sessions.add(session);
            student.setSessions(sessions);
        }else {
            student.getSessions().add(session);

        }
//        list.add(session);
//        student.setSessions(student.getSessions().add(session));
        studentRepository.save(student);
    }
    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar rabbitListenerEndpointRegistrar) {

    }
}
