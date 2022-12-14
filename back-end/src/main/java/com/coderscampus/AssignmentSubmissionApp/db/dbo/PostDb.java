package com.coderscampus.AssignmentSubmissionApp.db.dbo;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "post")
public class PostDb implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Timestamp created_date;

    private String content;

    @ManyToOne(optional = false)
    private UserDb creator;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getCreatedDate() {
        return created_date;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.created_date = createdDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public UserDb getCreator() {
        return creator;
    }

    public void setCreator(UserDb creator) {
        this.creator = creator;
    }

}
