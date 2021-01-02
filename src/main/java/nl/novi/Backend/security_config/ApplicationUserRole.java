package nl.novi.Backend.security_config;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum ApplicationUserRole {
    USER_FRO,
    USER_BAC,
    USER_TEC,
    USER_TRE,
    ADMIN

}