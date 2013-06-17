/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.spb.petrk.webissues.issues;

import java.util.Date;
import ru.spb.petrk.webissues.model.Issue;
import ru.spb.petrk.webissues.model.User;

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

    
    public IssueForm() {
        id = -1L;
    }
        
    public IssueForm(Issue issue) {
        this(issue.getId(), issue.getTitle(), issue.getDescription(), issue.getCreationDate(), issue.getCreator(), issue.getAssignee(), issue.getStatus(), issue.getPriority());
    }    

    public IssueForm(Long id, String title, String description, Date creationDate, User creator, User assignee, Status status, Priority priority) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.creationDate = creationDate;
        this.creator = creator;
        this.assignee = assignee;
        this.status = status;
        this.priority = priority;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public User getCreator() {
        return creator;
    }

    public User getAssignee() {
        return assignee;
    }

    public Status getStatus() {
        return status;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public void setAssignee(User assignee) {
        this.assignee = assignee;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }
    
    public void apply(Issue issue) {
        issue.setTitle(title);
        issue.setDescription(description);
        issue.setCreator(creator);
        issue.setCreationDate(creationDate);
        issue.setAssignee(assignee);
        issue.setPriority(priority);
        issue.setStatus(status);
    }
    
}
