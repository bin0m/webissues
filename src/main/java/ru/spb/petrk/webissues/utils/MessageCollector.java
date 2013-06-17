/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.spb.petrk.webissues.utils;

import ru.spb.petrk.webissues.utils.Pair;
import java.util.Collection;

/**
 *
 * @author PetrK
 */

public final class MessageCollector {
    
    private final StringBuilder builder = new StringBuilder();
    
    
    public void addMessage(Severity severity, String message) {
        switch (severity) {
            case error:                
                builder.append("<span style=\"color:red\">").append(message).append("</span>").append("<br>");
                break;
                
            default:
                builder.append(message).append("<br>");
        }
    }
    
    public void addMessages(Collection<Pair<Severity, String>> messages) {
        for (Pair<Severity, String> message : messages) {
            addMessage(message.getFirst(), message.getSecond());
        }
    }
    
    public String format() {
        return builder.toString();
    }
    
}
