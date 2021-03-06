package com.stackroute.MentorPlatformService.service;

import com.stackroute.MentorPlatformService.DAO.Message;
import com.stackroute.MentorPlatformService.DAO.Session;
import com.stackroute.MentorPlatformService.DAO.SessionDto;
import com.stackroute.MentorPlatformService.Repository.MessageRepository;
import com.stackroute.MentorPlatformService.Repository.SessionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WebSocketServiceImpl implements WebSocketService {
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private SessionRepository sessionRepository;
    private static final Logger logger = LoggerFactory.getLogger(WebSocketServiceImpl.class);
 //   private Message savedMessage;
//    String sender = "userOne";
//    String receiver = "userSecond";
    LocalDateTime sTime;
    LocalDateTime eTime;

    @Autowired
    public WebSocketServiceImpl(MessageRepository messageRepository, SessionRepository sessionRepository) {
        this.messageRepository = messageRepository;
        this.sessionRepository = sessionRepository;
    }

    @Override
    public Message saveMessage(@Payload Message message) {
      //  message.setTimeStamp(LocalDateTime.now().toString());
      //  message.setUserName(sender);
//        message.setReceiver(receiver);
        //savedMessage=messageRepository.save(message);
       return messageRepository.save(message);
    }

    @Override
    public Session updateSessionwithMessages(String sessionId,String messageId) {
//        if (sessionRepository.findById(sessionId).isPresent()) {
        logger.info("SessionId, MessageId retrieved:: " + sessionId + " " + messageId);
           Session session = sessionRepository.findById(sessionId).get();
            List<String> chatMessage = session.getSessionChatMessages();

            chatMessage.add(messageId);

            session.setSessionChatMessages(chatMessage);
            return sessionRepository.save(session);
//        }
//       else
//           return null;
    }

    @Override
    public Session startSession(Session session) {
        Session getDetails = new Session();
       // getDetails.setSessionId(session.getSessionId());
        getDetails.setStudentEmail(session.getStudentEmail());
        getDetails.setTeacherEmail(session.getTeacherEmail());
        getDetails.setStartTime(String.valueOf(LocalDateTime.now()));
        getDetails.setSessionStatus("Active");
        sTime=LocalDateTime.now();
        return sessionRepository.save(getDetails);
    }

    @Override
    public Session endSession(String sessionId) {
//        Session getDetails = new Session();
//        getDetails.setEndTime(session.getEndTime());
//        getDetails.setMessage(session.getMessage());
//        getDetails.setSessionChatMessages(session.getSessionChatMessages());
        Session session=sessionRepository.findBySessionId(sessionId);
        session.setSessionStatus("Ended");
        session.setEndTime(String.valueOf(LocalDateTime.now()));
        eTime = LocalDateTime.now();
        long minutes = ChronoUnit.MINUTES.between(eTime, sTime);
        long hours = ChronoUnit.HOURS.between(eTime, sTime);
        session.setDuration(String.valueOf(hours)+String.valueOf(minutes));
        return sessionRepository.save(session);
    }

    @Override
    public SessionDto getBySessionId(String sessionID) {
        logger.info("SessionID retrieved:: " + sessionID);
        Session session = sessionRepository.findById(sessionID).get();
//        session.getSessionChatMessages().forEach(m->messageRepository.findById(m));
        logger.info("Session retrieved:: " + session);
        SessionDto sessionDto = new SessionDto();

        List<Message> message = new ArrayList<>();
        List<String> l1 = session.getSessionChatMessages();
        for(String list_item: l1){
            logger.info("Message retrieved:: " + messageRepository.findById(list_item).get());
            message.add(messageRepository.findById(list_item).get());

        }
        sessionDto.setSessionId(session.getSessionId());
        sessionDto.setStartTime(session.getEndTime());
        sessionDto.setEndTime(session.getEndTime());
        sessionDto.setSessionChatMessages(message);
//        System.out.println(message);
        return sessionDto;
    }

    @Override
    public Session getMentorByEmailandStatus(String teacherEmail, String sessionStatus) {
        return sessionRepository.findByTeacherEmailAndSessionStatus(teacherEmail,sessionStatus);
    }

    @Override
    public Session getSession(String sessionID) {
        return  sessionRepository.findBySessionId(sessionID);
    }


}