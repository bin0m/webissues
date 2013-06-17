/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.spb.petrk.webissues.validators;

/**
 *
 * @author PetrK
 */
abstract class AbstractValidator<T> implements Validator<T> {
    
    protected boolean isEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }
    
}
