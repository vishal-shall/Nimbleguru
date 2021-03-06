package com.stackroute.MentorPlatformService.service;

import com.stackroute.MentorPlatformService.DAO.Message;
import com.stackroute.MentorPlatformService.DAO.Session;
import com.stackroute.MentorPlatformService.DAO.SessionDto;

import java.util.Optional;

public interface WebSocketService {

    Message saveMessage(Message message);
   Session updateSessionwithMessages(String sessionId,String messageId);
    Session startSession(Session session);
     Session endSession(String sessionId);
    SessionDto getBySessionId(String sessionID);
    Session getMentorByEmailandStatus(String teacherEmail, String sessionStatus);
    Session getSession(String sessionID);
}
