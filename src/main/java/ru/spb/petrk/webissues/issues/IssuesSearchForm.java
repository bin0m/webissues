/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.spb.petrk.webissues.issues;

/**
 *
 * @author PetrK
 */
public final class IssuesSearchForm {
    
    private final String title;
    
    private final Long creator;
    
    private final Long assignee;
    
    private final String priority;
    
    private final String status;

    public IssuesSearchForm(String title, Long creator, Long assignee, String priority, String status) {
        this.title = title;
        this.creator = creator;
        this.assignee = assignee;
        this.priority = priority;
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public Long getCreator() {
        return creator;
    }

    public Long getAssignee() {
        return assignee;
    }

    public String getPriority() {
        return priority;
    }

    public String getStatus() {
        return status;
    }
    
    public String toRequestParams() {
        return "title=" + notNull(title) + "&" +
               "creator=" + notNull(creator) + "&" +
               "assignee=" + notNull(assignee) + "&" +
               "priority=" + notNull(priority) + "&" +
               "status=" + notNull(status);
    }
    
    private Long notNull(Long obj) {
        return obj != null ? obj : -1L;
    }
    
    private String notNull(String obj) {
        return obj != null ? obj : "";
    }
    
}
