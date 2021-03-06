package com.stackroute.StudentProfileService.domain;


import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;


@Document
@Entity

public class Student {
    @Id
    private String id;
    private String name;
    private String phoneNumber;
    private int standard;
    private String email;
    private String password;
    List<Session> sessions;
    TimeSlot time;

    public Student() {
    }

    public Student(String id, String name, String phoneNumber, int standard, String email, String password, List<Session> sessions, TimeSlot time) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.standard = standard;
        this.email = email;
        this.password = password;
        this.sessions = sessions;
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getStandard() {
        return standard;
    }

    public void setStandard(int standard) {
        this.standard = standard;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Session> getSessions() {
        return sessions;
    }

    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }

    public TimeSlot getTime() {
        return time;
    }

    public void setTime(TimeSlot time) {
        this.time = time;
    }
}

