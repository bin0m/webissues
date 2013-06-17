/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.spb.petrk.webissues.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.spb.petrk.webissues.issues.IssueForm;
import ru.spb.petrk.webissues.issues.IssuesSearchForm;
import ru.spb.petrk.webissues.issues.Priority;
import ru.spb.petrk.webissues.issues.Status;
import ru.spb.petrk.webissues.model.Issue;
import ru.spb.petrk.webissues.model.User;
import ru.spb.petrk.webissues.repositories.IssuesRepository;
import ru.spb.petrk.webissues.repositories.UsersRepository;
import ru.spb.petrk.webissues.security.Permission;
import ru.spb.petrk.webissues.utils.Constants;
import ru.spb.petrk.webissues.utils.MessageCollector;
import ru.spb.petrk.webissues.utils.Severity;
import ru.spb.petrk.webissues.utils.Utils;
import ru.spb.petrk.webissues.validators.IssueFormValidator;

/**
 *
 * @author PetrK
 */

@Controller
public class ApplicationController {
    
    @Autowired
    private UsersRepository usersRepository;
    
    @Autowired
    private IssuesRepository issuesRepository;
    
    @PersistenceContext 
    private EntityManager entityManager;
    
    
    @RequestMapping(value = {"/app/", "/app/index"}, method = RequestMethod.GET)   
    public ModelAndView index() {             
        ModelAndView modelAndView = new ModelAndView("index"); 
        modelAndView.addObject(Constants.USERS, usersRepository.findAll());
        return modelAndView; 
    }
    
    @RequestMapping(value = {"/app/createIssue"}, method = RequestMethod.GET)  
    public ModelAndView createIssue() {
        ModelAndView modelAndView = new ModelAndView("edit_issue");
        modelAndView.addObject(Constants.USERS, usersRepository.findAll());
        return modelAndView;
    }
    
    @RequestMapping(value = {"/app/showIssue"}, method = RequestMethod.GET)  
    public ModelAndView showIssue(@RequestParam Long id) {
        ModelAndView modelAndView;
        
        Issue issue = issuesRepository.findOne(id);
        
        if (issue != null) {
            modelAndView = new ModelAndView("show_issue");
            modelAndView.addObject(Constants.ISSUE, issue);
        } else {
            MessageCollector collector = new MessageCollector();
            collector.addMessage(Severity.error, "Cannot find issue with id " + id + "");
            
            modelAndView = new ModelAndView("index");
            modelAndView.addObject(Constants.USERS, usersRepository.findAll());
            modelAndView.addObject(Constants.MESSAGE_COLLECTOR, collector);
        }
        
        return modelAndView;
    }    
    
    @RequestMapping(value = {"/app/editIssue"}, method = RequestMethod.GET)  
    public ModelAndView editIssue(@RequestParam Long id) {
        ModelAndView modelAndView;
        
        Issue issue = issuesRepository.findOne(id);
        
        if (issue != null) {
            modelAndView = new ModelAndView("edit_issue");
            modelAndView.addObject(Constants.USERS, usersRepository.findAll());
            modelAndView.addObject(Constants.ISSUE, issue);
        } else {
            MessageCollector collector = new MessageCollector();
            collector.addMessage(Severity.error, "Cannot find issue with id " + id + "");
            
            modelAndView = new ModelAndView("index");
            modelAndView.addObject(Constants.USERS, usersRepository.findAll());
            modelAndView.addObject(Constants.MESSAGE_COLLECTOR, collector);
        }
        
        return modelAndView;
    }
    
    @Transactional
    @RequestMapping(value = {"/app/saveIssue"}, method = RequestMethod.POST)  
    public ModelAndView saveIssue(HttpServletRequest request,
                                  @RequestParam(required = false) Long id,
                                  @RequestParam String title,
                                  @RequestParam String description,
                                  @RequestParam Long assignee,
                                  @RequestParam String priority,
                                  @RequestParam String status,
                                  Principal principal)
    {
        User visitor = usersRepository.findByLogin(principal.getName());
        
        boolean newIssue = Utils.isEmpty(id);
        
        IssueForm issueForm;
        
        if (newIssue) {
            issueForm = new IssueForm();
            issueForm.setCreator(visitor);
            issueForm.setCreationDate(new Date());                        
        } else {
            issueForm = new IssueForm(issuesRepository.findOne(id));
        }
        
        issueForm.setTitle(title);
        issueForm.setDescription(description);
        issueForm.setAssignee(assignee != null ? usersRepository.findOne(assignee) : null);
        issueForm.setPriority(Priority.valueOf(priority));
        issueForm.setStatus(Status.valueOf(status));
        
        MessageCollector collector = new MessageCollector();           
        IssueFormValidator validator = new IssueFormValidator(visitor, newIssue);
        
        ModelAndView modelAndView;
        
        if (validator.validate(collector, issueForm)) {
            Issue issue = newIssue ? new Issue() : issuesRepository.findOne(id);
            
            issueForm.apply(issue);
            
            issuesRepository.save(issue);            

            if (!newIssue) {
                IssuesSearchForm searchForm = (IssuesSearchForm) request.getSession().getAttribute(Constants.BEAN_SEARCH_ISSUES_FORM);
                if (searchForm != null) {
                    List<Issue> issues = searchIssues(
                            visitor, 
                            searchForm.getCreator(), 
                            searchForm.getAssignee(), 
                            searchForm.getTitle(),
                            searchForm.getPriority(), 
                            searchForm.getStatus()
                    );
                    
                    modelAndView = new ModelAndView("issues");
                    modelAndView.addObject(Constants.ISSUES, issues);
                } else {
                    modelAndView = new ModelAndView("index");
                }
            } else {
                modelAndView = new ModelAndView("index");
            }

            modelAndView.addObject(Constants.USERS, usersRepository.findAll());
            
        } else {
            modelAndView = new ModelAndView("edit_issue");
            modelAndView.addObject(Constants.ISSUE, issueForm);
            modelAndView.addObject(Constants.USERS, usersRepository.findAll());
            modelAndView.addObject(Constants.MESSAGE_COLLECTOR, collector);
        }

        return modelAndView;
    }          
    
    @Transactional
    @RequestMapping(value = {"/app/search"}, method = RequestMethod.GET)   
    public ModelAndView search(HttpServletRequest request,
                               @RequestParam Long creator, 
                               @RequestParam Long assignee,
                               @RequestParam String title,
                               @RequestParam String priority,
                               @RequestParam String status,
                               Principal principal) 
    {
        User visitor = usersRepository.findByLogin(principal.getName());
        
        request.getSession().setAttribute(Constants.BEAN_SEARCH_ISSUES_FORM, new IssuesSearchForm(title, creator, assignee, priority, status));
        
        List<Issue> issues = searchIssues(visitor, creator, assignee, title, priority, status);
        
        ModelAndView modelAndView = new ModelAndView("issues");
        modelAndView.addObject(Constants.ISSUES, issues);        
        return modelAndView; 
    }    
    
    @Transactional
    @RequestMapping(value = {"/app/searchForVisitor"}, method = RequestMethod.GET)   
    public ModelAndView searchForVisitor(HttpServletRequest request, Principal principal) 
    {       
        User visitor = usersRepository.findByLogin(principal.getName());
        
        request.getSession().setAttribute(Constants.BEAN_SEARCH_ISSUES_FORM, new IssuesSearchForm("", null, visitor.getId(), null, null));
        
        List<Issue> issues = searchIssues(visitor, null, visitor.getId(), null, null, null);
        
        ModelAndView modelAndView = new ModelAndView("issues");
        modelAndView.addObject(Constants.ISSUES, issues);        
        return modelAndView; 
    }    
    
    
    private List<Issue> searchIssues(User visitor, Long creator, Long assignee, String title, String priority, String status) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Issue> criteriaQuery = criteriaBuilder.createQuery(Issue.class);
        
        Root<Issue> from = criteriaQuery.from(Issue.class);
        
        List<Predicate> predicates = new ArrayList<Predicate>();
        
        if (!Utils.isEmpty(creator)) {
            predicates.add(criteriaBuilder.equal(from.get("creator"), creator));
        }
        
        if (!Utils.isEmpty(assignee)) {
            predicates.add(criteriaBuilder.equal(from.get("assignee"), assignee));
        }
        
        if (!Utils.isEmpty(title)) {
            predicates.add(criteriaBuilder.like(from.<String>get("title"), "%" + title + "%"));
        }
        
        if (!Utils.isEmpty(priority)) {
            predicates.add(criteriaBuilder.equal(from.get("priority"), Priority.valueOf(priority)));
        }
        
        if (!Utils.isEmpty(status)) {
            predicates.add(criteriaBuilder.equal(from.get("status"), Status.valueOf(status)));
        }
        
        if (predicates.size() > 0) {
            criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]));
        }
        
        TypedQuery<Issue> typedQuery = entityManager.createQuery(criteriaQuery.select(from));
        
        List<Issue> issues = typedQuery.getResultList();
        
        issues = Utils.filter(issues, new IssuesFilter(visitor));
        
        Collections.sort(issues, new IssuesComparator());
        
        return issues;
    }
    

    private static class IssuesComparator implements Comparator<Issue> {

        @Override
        public int compare(Issue o1, Issue o2) {            
            if (o1.getStatus().getSortPriority() != o2.getStatus().getSortPriority()) {
                return o1.getStatus().getSortPriority() - o2.getStatus().getSortPriority();
            }
            
            if (o1.getPriority().getSortPriority() != o2.getPriority().getSortPriority()) {
                return o1.getPriority().getSortPriority() - o2.getPriority().getSortPriority();
            }
            
            return o1.getCreationDate().compareTo(o2.getCreationDate());
        }        
        
    }
    
    private static class IssuesFilter implements Utils.Filter<Issue> {

        private final User visitor;

        public IssuesFilter(User visitor) {
            this.visitor = visitor;
        }

        @Override
        public boolean accept(Issue element) {
            if (visitor.isGranted(Permission.VIEW_ALL)) {
                return true;
            }
            if (visitor.isGranted(Permission.VIEW_SELF)) {
                if (visitor.equals(element.getAssignee()) || visitor.equals(element.getCreator())) {
                    return true;
                }
            }
            return false;
        }

    }
    
}
