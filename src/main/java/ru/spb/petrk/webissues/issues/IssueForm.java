/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.spb.petrk.webissues.issues;

import ru.spb.petrk.webissues.model.Issue;
import ru.spb.petrk.webissues.model.User;

import java.util.Date;

/**
 *
 * @author PetrK
 */
public final class IssueForm {
    
    private final Long id;
    
    private String title;    
    
    private String description;
    
    private Date creationDate;
        
    private User creator;
    
    private User assignee;

    private Status status;

    private Priority priority;

    private String hashtag;

    
    public IssueForm() {
        id = -1L;
    }
        
    public IssueForm(Issue issue) {
        this(issue.getId(), issue.getTitle(), issue.getDescription(), issue.getCreationDate(), issue.getCreator(), issue.getAssignee(), issue.getStatus(), issue.getPriority(), issue.getHashtag());
    }

    public IssueForm(Long id, String title, String description, Date creationDate, User creator, User assignee, Status status, Priority priority, String hashtag) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.creationDate = creationDate;
        this.creator = creator;
        this.assignee = assignee;
        this.status = status;
        this.priority = priority;
        this.hashtag = hashtag;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public User getAssignee() {
        return assignee;
    }

    public void setAssignee(User assignee) {
        this.assignee = assignee;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public String getHashtag() {
        return hashtag;
    }

    public void setHashtag(String hashtag) {
        this.hashtag = hashtag;
    }
    
    public void apply(Issue issue) {
        issue.setTitle(title);
        issue.setDescription(description);
        issue.setCreator(creator);
        issue.setCreationDate(creationDate);
        issue.setAssignee(assignee);
        issue.setPriority(priority);
        issue.setStatus(status);
        issue.setHashtag(hashtag);
    }
    
}
