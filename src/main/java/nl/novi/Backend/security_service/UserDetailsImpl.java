package nl.novi.Backend.security_service;

import com.fasterxml.jackson.annotation.JsonIgnore;

import nl.novi.Backend.model.AppUser;
import nl.novi.Backend.model.Role;
import nl.novi.Backend.security_config.PasswordConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;
import java.util.stream.Collectors;

    public class UserDetailsImpl implements UserDetails {
        private AppUser appUser;
       
        public UserDetailsImpl(AppUser appUser) {
            this.appUser = appUser;
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            List<GrantedAuthority> authorities = appUser.getRoles().stream()
                    .map(role -> new SimpleGrantedAuthority(role.getRoleName().name()))
                    .collect(Collectors.toList());

            return authorities;
        }


        @Override
        public String getPassword() {

            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
            String result = encoder.encode(appUser.getPassword());

            return result;
        }

        @Override
        public String getUsername() {
            return appUser.getUserName();
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }
    }
