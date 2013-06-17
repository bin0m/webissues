/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.spb.petrk.webissues.utils;

/**
 *
 * @author PetrK
 */
public class Pair<F, S> {
    
    private final F first;
    
    private final S second;
    
    
    public static <F, S> Pair<F, S> from(F f, S s) {
        return new Pair<F, S>(f, s);
    }
    

    public Pair(F first, S second) {
        this.first = first;
        this.second = second;
    }
    
    public F getFirst() {
        return first;
    }

    public S getSecond() {
        return second;
    }
}
