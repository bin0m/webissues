/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.spb.petrk.webissues.validators;

import ru.spb.petrk.webissues.issues.IssueForm;
import ru.spb.petrk.webissues.model.Issue;
import ru.spb.petrk.webissues.model.User;
import ru.spb.petrk.webissues.security.Permission;
import ru.spb.petrk.webissues.utils.MessageCollector;
import ru.spb.petrk.webissues.utils.Severity;

/**
 *
 * @author PetrK
 */
public class IssueFormValidator extends AbstractValidator<IssueForm> {
    
    private final User visitor;
    
    private final boolean newIssue;

    
    public IssueFormValidator(User visitor, boolean newIssue) {
        this.visitor = visitor;
        this.newIssue = newIssue;
    }

    @Override
    public boolean validate(MessageCollector collector, IssueForm target) {
        boolean result = true;        
        
        if (!newIssue) {
            if (!visitor.isGranted(Permission.EDIT_ALL)) {
                if (visitor.isGranted(Permission.EDIT_SELF)) {
                    if (!visitor.equals(target.getCreator()) && !visitor.equals(target.getAssignee())) {
                        collector.addMessage(Severity.error, "Вы не можете редактировать это задание");
                        result = false;                        
                    }
                } else {
                    collector.addMessage(Severity.error, "Вы не можете редактировать это задание");
                    result = false;
                }
            }
        } else {
            if (!visitor.isGranted(Permission.CREATE)) {
                collector.addMessage(Severity.error, "Вам запрещено создавать задания");
                result = false;
            }
        }
        
        if (result) {
            if (isEmpty(target.getTitle())) {
                collector.addMessage(Severity.error, "Поле 'Тема' не может быть пустым");
                result = false;
            }
        }
        
        return result;
    } 
}
