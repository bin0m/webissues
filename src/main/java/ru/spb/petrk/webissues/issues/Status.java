/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.spb.petrk.webissues.issues;

import ru.spb.petrk.webissues.utils.Identifiable;
import ru.spb.petrk.webissues.utils.Sortable;

/**
 *
 * @author PetrK
 */
public enum Status implements Identifiable<String>, Sortable {    
    open(0),
    started(1),
    closed(2);
    
    private final int sortPriority;

    private Status(int sortPriority) {
        this.sortPriority = sortPriority;
    }

    @Override
    public String getId() {
        return name();
    }

    @Override
    public int getSortPriority() {
        return sortPriority;
    }
    
}
