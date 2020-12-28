package nl.novi.Backend.security_service;

import nl.novi.Backend.security_config.ApplicationUserRole;
import nl.novi.Backend.security_model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


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

    public ApplicationUserDetails(User user) {
        this.username = user.getUserName();
        this.password = user.getPassword();
        this.grandedAuthorities = user.getRole().getGrantedAuthorities();
        this.isAccountNonExpired=user.getAccountNonExpired();
        this.isAccountNonLocked = user.getAccountNonLocked();
        this.isCredentialsNonExpired = user.getCredentialsNonExpired();
        this.isEnabled = user.isEnabled();
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


