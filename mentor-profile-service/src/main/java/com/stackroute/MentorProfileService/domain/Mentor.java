package com.stackroute.MentorProfileService.domain;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Entity
@Document
public class Mentor
{
    @Id
    private String id;
    String email;
    String phoneNumber;
    String name;
    String qualification;
    List<String> subject;
    String status;
    String joining_date;
    float creditStore;
    float rating;
    List<TimeSlot> time;
    List<Session> sessions;
    String comment;

    public Mentor() {
    }

    public Mentor( String id, String email, String phoneNumber, String name, String qualification, List<String> subject, String status, String joining_date, float creditStore, float rating, List<TimeSlot> time, List<Session> sessions, String comment) {
        this.id = id;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.qualification = qualification;
        this.subject = subject;
        this.status = status;
        this.joining_date = joining_date;
        this.creditStore = creditStore;
        this.rating = rating;
        this.time = time;
        this.sessions = sessions;
        this.comment = comment;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public List<String> getSubject() {
        return subject;
    }

    public void setSubject(List<String> subject) {
        this.subject = subject;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getJoining_date() {
        return joining_date;
    }

    public void setJoining_date(String joining_date) {
        this.joining_date = joining_date;
    }

    public float getCreditStore() {
        return creditStore;
    }

    public void setCreditStore(float creditStore) {
        this.creditStore = creditStore;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public List<TimeSlot> getTime() {
        return time;
    }

    public void setTime(List<TimeSlot> time) {
        this.time = time;
    }

    public List<Session> getSessions() {
        return sessions;
    }

    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Mentor{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", name='" + name + '\'' +
                ", qualification='" + qualification + '\'' +
                ", subject=" + subject +
                ", status='" + status + '\'' +
                ", joining_date='" + joining_date + '\'' +
                ", creditStore=" + creditStore +
                ", rating=" + rating +
                ", time=" + time +
                ", sessions=" + sessions +
                ", comment='" + comment + '\'' +
                '}';
    }
}
