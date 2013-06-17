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
public enum Priority implements Identifiable<String>, Sortable {    
    low(2),
    medium(1),
    high(0);
    
    private final int sortPriority;

    private Priority(int sortPriority) {
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
