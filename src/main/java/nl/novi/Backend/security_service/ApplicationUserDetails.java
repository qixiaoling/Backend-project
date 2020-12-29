package nl.novi.Backend.security_service;

import nl.novi.Backend.model.AppUser;

import nl.novi.Backend.security_config.ApplicationUserRole;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;


public class ApplicationUserDetails implements UserDetails {

    private String username;
    private String password;
    private Set<? extends GrantedAuthority> grandedAuthorities;
    private  Boolean isAccountNonExpired;
    private  Boolean isAccountNonLocked;
    private  Boolean isCredentialsNonExpired;
    private Boolean isEnabled;
    @Autowired
    ApplicationUserRole applicationUserRole;

    public ApplicationUserDetails(AppUser appuser) {
        this.username = appuser.getUserName();
        this.password = appuser.getPassword();
        this.grandedAuthorities = appuser.getRole().getRoleName().getGrantedAuthorities();
        this.isAccountNonExpired=appuser.getAccountNonExpired();
        this.isAccountNonLocked = appuser.getAccountNonLocked();
        this.isCredentialsNonExpired = appuser.getCredentialsNonExpired();
        this.isEnabled = appuser.isEnabled();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grandedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }
}


