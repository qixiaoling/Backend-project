package nl.novi.Backend.security;

public enum ApplicationUserPermission {
    CUSTOMER_READ("customer:read"),
    CUSTOMER_WRITE("customer:write"),
    CAR_READ("car:read"),
    CAR_WRITE("car:write"),
    INSPECTION_READ("inspection:read"),
    INSPECTION_WRITE("inspection:write"),
    INVENTORY_READ("inventory:read"),
    INVENTORY_WRITE("inventory:write"),
    INVOICE_READ("invoice:read"),
    INVOICE_WRITE("invoice:write");

    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
