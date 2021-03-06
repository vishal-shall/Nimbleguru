package com.stackroute.MentorPlatformService.domain;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
@Document
public class Faq1 {

    String subject;
    String query;
    String solution;

    public Faq1() {
    }

    public Faq1(String subject, String query, String solution) {
        this.subject = subject;
        this.query = query;
        this.solution = solution;
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

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }
}
