package nl.novi.Backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import nl.novi.Backend.security_config.ApplicationUserRole;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long role_id;
    @Column
    @Enumerated(EnumType.STRING)
    private ApplicationUserRole roleName;
    @ManyToMany (fetch = FetchType.EAGER, cascade = CascadeType.PERSIST, mappedBy = "roles")
    private Set<AppUser> appUsers;

    public Role() {
    }

    public Role(Long role_id, ApplicationUserRole roleName) {
        this.role_id = role_id;
        this.roleName = roleName;
    }

    public long getRole_id() {
        return role_id;
    }

    public void setRole_id(Long role_id) {
        this.role_id = role_id;
    }

    public ApplicationUserRole getRoleName() {
        return roleName;
    }

    public void setRoleName(ApplicationUserRole roleName) {
        this.roleName = roleName;
    }

    public Set<AppUser> getAppUsers() {
        return appUsers;
    }

    public void setAppUsers(Set<AppUser> appUsers) {
        this.appUsers = appUsers;
    }
}


