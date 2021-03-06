package com.stackroute.MentorPlatformService.DAO;

import java.util.ArrayList;
import java.util.List;

public class SessionDto {
    private String sessionId;
    private String message;
    private String startTime;
    private String endTime;
    private List<Message> sessionChatMessages=new ArrayList<>();

    public SessionDto() {
    }

    public SessionDto(String sessionId, String message, String startTime, String endTime, List<Message> sessionChatMessages) {
        this.sessionId = sessionId;
        this.message = message;
        this.startTime = startTime;
        this.endTime = endTime;
        this.sessionChatMessages = sessionChatMessages;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public List<Message> getSessionChatMessages() {
        return sessionChatMessages;
    }

    public void setSessionChatMessages(List<Message> sessionChatMessages) {
        this.sessionChatMessages = sessionChatMessages;
    }
}

