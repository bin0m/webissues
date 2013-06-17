package ru.spb.petrk.webissues.security;

/**
 *
 * @author PetrK
 */
public interface AuthorizationContext {
    
    boolean hasRole(Role role);
    
    boolean isGranted(Permission permission);

}
