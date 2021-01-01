package nl.novi.Backend.security_config;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum ApplicationUserRole {
    ROLE_USER_FRO,
    ROLE_USER_BAC,
    ROLE_USER_TEC,
    ROLE_USER_TRE,
    ROLE_ADMIN

}