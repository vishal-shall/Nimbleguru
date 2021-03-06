package com.stackroute.MentorProfileService.service;

import com.stackroute.MentorProfileService.domain.Mentor;
import com.stackroute.MentorProfileService.domain.Session;
import com.stackroute.MentorProfileService.repository.MentorRepository;
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
    private MentorRepository mentorRepository;
    private static final Logger logger = LoggerFactory.getLogger(RabbitMqReciever.class);


    public RabbitMqReciever(MentorRepository mentorRepository) {
        this.mentorRepository = mentorRepository;
    }

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void recievedMessage(Session session) {
        logger.info("Recieved programmer details" + session.toString());

//        Mentor getDetails = new Mentor();
//        getDetails.setSessions(mentor.getSessions());
        Mentor mentor = mentorRepository.findByEmail(session.getTeacherEmail());
        if (mentor.getSessions() == null) {
            List<Session> sessions = new ArrayList<>();
            sessions.add(session);
            mentor.setSessions(sessions);

        } else {
            mentor.getSessions().add(session);
        }
        mentorRepository.save(mentor);


    }

    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar rabbitListenerEndpointRegistrar) {

    }
}
