package nl.novi.Backend.security_service;

import com.fasterxml.jackson.annotation.JsonIgnore;

import nl.novi.Backend.model.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

    public class UserDetailsImpl implements UserDetails {

        @Autowired
        PasswordEncoder passwordEncoder;

        private final Long id;
        private final String username;
        private final String password;
        private final Collection<? extends GrantedAuthority> authorities;

        public UserDetailsImpl(Long id, String username, String password,
                               Collection<? extends GrantedAuthority> authorities) {
            this.id = id;
            this.username = username;
            this.password = password;
            this.authorities = authorities;
        }

        public static UserDetailsImpl build(AppUser appUser) {
            List<GrantedAuthority> authorities = appUser.getRoles().stream()
                    .map(role -> new SimpleGrantedAuthority(role.getRoleName().name()))
                    .collect(Collectors.toList());
            BCryptPasswordEncoder pas = new BCryptPasswordEncoder();
            String EPassword = pas.encode(appUser.getPassword());

            return new UserDetailsImpl(
                    appUser.getUser_id(),
                    appUser.getUserName(),
                    EPassword,
                    authorities);
        }

        public Long getId() {
            return id;
        }


        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return authorities;
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
