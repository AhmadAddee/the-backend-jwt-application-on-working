package com.coderscampus.AssignmentSubmissionApp.db.dbo;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "message")
public class MessageDb implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Timestamp sent_date;

    private String content;

    @ManyToOne(optional = false)
    private UserDb receiver;

    @ManyToOne(optional = false)
    private UserDb sender;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getSentDate() {
        return sent_date;
    }

    public void setSentDate(Timestamp sentDate) {
        this.sent_date = sentDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public UserDb getSender() {
        return sender;
    }

    public void setSender(UserDb sender) {
        this.sender = sender;
    }

    public UserDb getReceiver() {
        return receiver;
    }

    public void setReceiver(UserDb receiver) {
        this.receiver = receiver;
    }
}
