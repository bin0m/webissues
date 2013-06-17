package ru.spb.petrk.webissues.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import ru.spb.petrk.webissues.issues.Priority;
import ru.spb.petrk.webissues.issues.Status;

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

        
    //<editor-fold defaultstate="collapsed" desc="Getters/Setters">
    
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
    
    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }    
    
    //</editor-fold>

}
