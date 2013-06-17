/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.spb.petrk.webissues.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import ru.spb.petrk.webissues.issues.Priority;
import ru.spb.petrk.webissues.issues.Status;
import ru.spb.petrk.webissues.security.Role;

/**
 *
 * @author PetrK
 */
public class ContentHelperImpl implements ContentHelper {   
    
    private static final String DEFAULT_FORMAT = "dd-MM-yyyy";
    

    @Override
    public List<Role> getRoles() {
        return Arrays.asList(Role.values());
    }

    @Override
    public List<Status> getStatuses() {
        return Arrays.asList(Status.values());
    }

    @Override
    public List<Priority> getPriorities() {
        return Arrays.asList(Priority.values());
    }

    @Override
    public String format(Date date, String formatString) {
        DateFormat dateFormat = new SimpleDateFormat(formatString != null ? formatString : DEFAULT_FORMAT);
        return dateFormat.format(date);
    }
}
