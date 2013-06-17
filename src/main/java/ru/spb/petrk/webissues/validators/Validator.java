/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.spb.petrk.webissues.validators;

import ru.spb.petrk.webissues.utils.MessageCollector;

/**
 *
 * @author PetrK
 */
public interface Validator<T> {
    
    boolean validate(MessageCollector collector, T target);
    
}
