package nl.novi.Backend.security_config;

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



        @Override //the person that has been found inside the database, what was the roles that he suppose to have?
        public Collection<? extends GrantedAuthority> getAuthorities() {
            List<GrantedAuthority> authorities = appUser.getRoles().stream()
                    .map(role -> new SimpleGrantedAuthority(role.getRoleName().name()))
                    .collect(Collectors.toList());

            return authorities;
        }


        @Override
        public String getPassword() {

            /*BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
            String result = encoder.encode(appUser.getPassword());

            return result;*/

            //what was the password he registered to have inside the database.
            return appUser.getPassword();
        }

        @Override//what was the username registered inside the database.
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
