package ru.spb.petrk.webissues.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import ru.spb.petrk.webissues.security.AuthorizationContext;
import ru.spb.petrk.webissues.security.Permission;
import ru.spb.petrk.webissues.security.Role;

/**
 *
 * @author PetrK
 */

@Entity
@Table(name = "users")
public class User extends AbstractEntity implements AuthorizationContext {
    
    @Column(name = "login")
    private String login;
    
    @Column(name = "first_name")
    private String firstName;
    
    @Column(name = "last_name")
    private String lastName;
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "password")
    private String password; // bad but for study project is ok
    
    @Column(name = "blocked")
    private boolean blocked;
    
    @OneToMany(mappedBy = "creator")
    private List<Issue> createdIssues = emptyList();    
    
    @OneToMany(mappedBy = "assignee")
    private List<Issue> assignedIssues = emptyList();
        
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @Column(name = "roles")
    private List<Role> roles = emptyList();

    
    @Override
    public boolean hasRole(Role role) {
        for (Role userRole : roles) {
            if (userRole.hasRole(role)) {
                return true;
            }
        }
        return false;        
    }

    @Override
    public boolean isGranted(Permission permission) {
        for (Role role : roles) {
            if (role.isGranted(permission)) {
                return true;
            }
        }
        return false;
    }
    
    public String getFio() {
        return firstName + " " + lastName;
    }
    
    //<editor-fold defaultstate="collapsed" desc="Getters/Setters">
    
    public String getLogin() {
        return login;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public String getPassword() {
        return password;
    }
    
    public List<Role> getRoles() {
        return roles;
    }
    
    public void setLogin(String login) {
        this.login = login;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
    
    public List<Issue> getCreatedIssues() {
        return createdIssues;
    }

    public void setCreatedIssues(List<Issue> createdIssues) {
        this.createdIssues = createdIssues;
    }

    public List<Issue> getAssignedIssues() {
        return assignedIssues;
    }

    public void setAssignedIssues(List<Issue> assignedIssues) {
        this.assignedIssues = assignedIssues;
    }    
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }    
    
    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }    
        
    //</editor-fold>
    
}
