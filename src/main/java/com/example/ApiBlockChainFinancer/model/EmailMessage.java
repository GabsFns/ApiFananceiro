package com.example.ApiBlockChainFinancer.model;


import java.io.Serializable;

public class EmailMessage implements Serializable {
    private String to;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    private String subject;
    private String body;

    public EmailMessage(){}

    public EmailMessage(String to, String subject, String body){
        this.to = to;
        this.subject = subject;
        this.body = body;
    }
}
