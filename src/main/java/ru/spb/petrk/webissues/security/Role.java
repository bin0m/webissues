package ru.spb.petrk.webissues.security;

import ru.spb.petrk.webissues.utils.Identifiable;

/**
 *
 * @author PetrK
 */
public enum Role implements AuthorizationContext, Identifiable<String> {
    
    Admin(Permission.CREATE, Permission.DELETE, Permission.VIEW_ALL, Permission.EDIT_ALL),
    
    User(Permission.CREATE, Permission.VIEW_SELF, Permission.EDIT_SELF),
    
    Observer(Permission.VIEW_ALL);
    

    @Override
    public boolean hasRole(Role role) {
        return this == role;
    }
    
    public boolean isGranted(Permission permission) {
        for (Permission granted : permissions) {
            if (granted.includes(permission)) {
                return true;
            }
        }
        return false;
    }    
    
    @Override
    public String getId() {
        return name();
    }
    
    //<editor-fold defaultstate="collapsed" desc="hidden">
    private Permission[] permissions;
    
    private Role(Permission ... permissions) {
        this.permissions = permissions;
    }
    //</editor-fold>    
}
