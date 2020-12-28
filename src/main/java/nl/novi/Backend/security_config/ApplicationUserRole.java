package nl.novi.Backend.security_config;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum ApplicationUserRole{
    USER_FRO(Sets.newHashSet(
            ApplicationUserPermission.CUSTOMER_READ,
            ApplicationUserPermission.CUSTOMER_WRITE,
            ApplicationUserPermission.CAR_READ,
            ApplicationUserPermission.CAR_WRITE,
            ApplicationUserPermission.INSPECTION_READ,
            ApplicationUserPermission.INSPECTION_WRITE,
            ApplicationUserPermission.INVENTORY_READ,
            ApplicationUserPermission.INVOICE_READ
    )),
    USER_BAC(Sets.newHashSet(
            ApplicationUserPermission.CUSTOMER_READ,
            ApplicationUserPermission.CAR_READ,
            ApplicationUserPermission.INSPECTION_READ,
            ApplicationUserPermission.INVENTORY_READ,
            ApplicationUserPermission.INVENTORY_WRITE,
            ApplicationUserPermission.INVOICE_READ
    )),
    USER_TEC(Sets.newHashSet(
            ApplicationUserPermission.CUSTOMER_READ,
            ApplicationUserPermission.CAR_READ,
            ApplicationUserPermission.INSPECTION_READ,
            ApplicationUserPermission.INSPECTION_WRITE,
            ApplicationUserPermission.INVENTORY_READ,
            ApplicationUserPermission.INVOICE_READ
    )),
    USER_TRE(Sets.newHashSet(
            ApplicationUserPermission.CUSTOMER_READ,
            ApplicationUserPermission.CAR_READ,
            ApplicationUserPermission.INSPECTION_READ,
            ApplicationUserPermission.INVENTORY_READ,
            ApplicationUserPermission.INVOICE_READ,
            ApplicationUserPermission.INVOICE_WRITE
    )),

    ADMIN(Sets.newHashSet(
            ApplicationUserPermission.CUSTOMER_READ,
            ApplicationUserPermission.CUSTOMER_WRITE,
            ApplicationUserPermission.CAR_READ,
            ApplicationUserPermission.CAR_WRITE,
            ApplicationUserPermission.INSPECTION_READ,
            ApplicationUserPermission.INSPECTION_WRITE,
            ApplicationUserPermission.INVENTORY_READ,
            ApplicationUserPermission.INVENTORY_WRITE,
            ApplicationUserPermission.INVOICE_READ,
            ApplicationUserPermission.INVOICE_WRITE
    ));

    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities(){
        Set<SimpleGrantedAuthority> permissions= getPermissions().stream()
                .map(permission-> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_"+this.name()));
        return permissions;
    }
}
