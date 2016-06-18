package ru.spb.petrk.webissues.model;

import ru.spb.petrk.webissues.issues.Priority;
import ru.spb.petrk.webissues.issues.Status;

import javax.persistence.*;
import java.util.Date;

/**
 *
 * @author PetrK
 */

@Entity
@Table(name = "issues")
public class Issue extends AbstractEntity {
    
    @Column(name = "title")
    private String title;    
    
    @Column(name = "description")
    private String description;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creation_date")
    private Date creationDate;
    
    @ManyToOne
    @JoinColumn(name="creator")
    private User creator;
    
    @ManyToOne
    @JoinColumn(name="assignee")
    private User assignee;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "priority")
    private Priority priority;

    @Column(name = "hashtag")
    private String hashtag;

        
    //<editor-fold defaultstate="collapsed" desc="Getters/Setters">
    
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

    public String getHashtag() {
        return hashtag;
    }

    public void setHashtag(String hashtag) {
        this.hashtag = hashtag;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }
    
    //</editor-fold>

}
