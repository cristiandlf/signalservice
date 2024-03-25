package cl.ckrchile.responsibleservice.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Roles implements GrantedAuthority {
    R_RESPONSIBLE_MANAGER,
    SUPERVISOR;
    @Override
    public String getAuthority() {
        return this.name();
    }
}