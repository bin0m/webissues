/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.spb.petrk.webissues.utils;

import java.util.Date;
import java.util.List;
import ru.spb.petrk.webissues.issues.Priority;
import ru.spb.petrk.webissues.issues.Status;
import ru.spb.petrk.webissues.security.Role;

/**
 *
 * @author PetrK
 */
public interface ContentHelper {
    
    List<Role> getRoles();
    
    List<Status> getStatuses();
    
    List<Priority> getPriorities();
    
    String format(Date date, String formatString);
    
}
