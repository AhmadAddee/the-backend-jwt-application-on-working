package com.coderscampus.AssignmentSubmissionApp.dto;

import java.util.Date;

public class Message {
    private Long id;
    private Date sentDate;
    private String content;
    private String sender;
    private String receiver;
    private String timeAgo;

    public Message() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getSentDate() {
        return sentDate;
    }

    public void setSentDate(Date sentDate) {
        this.sentDate = sentDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getTimeAgo() {
        return timeAgo;
    }

    public void setTimeAgo(String timeAgo) {
        this.timeAgo = timeAgo;
    }

    @Override
    public String toString() {
        return "Message{" +
               "id=" + id +
               ", sentDate=" + sentDate +
               ", content='" + content + '\'' +
               ", sender='" + sender + '\'' +
               ", receiver='" + receiver + '\'' +
               ", timeAgo='" + timeAgo + '\'' +
               '}';
    }
}
