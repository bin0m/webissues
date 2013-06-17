/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.spb.petrk.webissues.security;

/**
 *
 * @author PetrK
 */
public enum Permission {    
    CREATE,
    DELETE,
    
    VIEW_SELF,    
    EDIT_SELF,
    
    VIEW_ALL(VIEW_SELF),
    EDIT_ALL(EDIT_SELF);

    
    public boolean includes(Permission permission) {
        if (this == permission) {
            return true;
        }
        
        if (includedPermissions != null) {
            for (Permission included : includedPermissions) {
                if (included.includes(permission)) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    //<editor-fold defaultstate="collapsed" desc="hidden">
    
    private final Permission[] includedPermissions;
    
    private Permission(Permission ... includedPermissions) {
        this.includedPermissions = includedPermissions;
    }
    
    //</editor-fold>
}
