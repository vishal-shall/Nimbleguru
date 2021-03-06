package com.stackroute.MentorProfileService.domain;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Session {
    @Id
    private String sessionId;
    private String message;
    private String sessionVideoUrl;
    private String startTime;
    private String endTime;
    private String studentEmail;
    private String teacherEmail;
    private List<String> sessionChatMessages=new ArrayList<>();
    private String duration;
    private String subject;
    private String query;
    private String sessionStatus;

    public Session() {
    }

    public Session(String sessionId, String message, String sessionVideoUrl, String startTime, String endTime, String studentEmail, String teacherEmail, List<String> sessionChatMessages, String duration, String subject, String query, String sessionStatus) {
        this.sessionId = sessionId;
        this.message = message;
        this.sessionVideoUrl = sessionVideoUrl;
        this.startTime = startTime;
        this.endTime = endTime;
        this.studentEmail = studentEmail;
        this.teacherEmail = teacherEmail;
        this.sessionChatMessages = sessionChatMessages;
        this.duration = duration;
        this.subject = subject;
        this.query = query;
        this.sessionStatus = sessionStatus;
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

    public String getSessionVideoUrl() {
        return sessionVideoUrl;
    }

    public void setSessionVideoUrl(String sessionVideoUrl) {
        this.sessionVideoUrl = sessionVideoUrl;
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

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public String getTeacherEmail() {
        return teacherEmail;
    }

    public void setTeacherEmail(String teacherEmail) {
        this.teacherEmail = teacherEmail;
    }

    public List<String> getSessionChatMessages() {
        return sessionChatMessages;
    }

    public void setSessionChatMessages(List<String> sessionChatMessages) {
        this.sessionChatMessages = sessionChatMessages;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getSessionStatus() {
        return sessionStatus;
    }

    public void setSessionStatus(String sessionStatus) {
        this.sessionStatus = sessionStatus;
    }
}


