package nl.novi.Backend.security;

import com.google.common.collect.Sets;

import java.util.Set;

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
}
