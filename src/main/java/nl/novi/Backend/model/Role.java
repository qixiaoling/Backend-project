package nl.novi.Backend.model;

import nl.novi.Backend.security_config.ApplicationUserRole;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long role_id;
    @Column
    @Enumerated(EnumType.STRING)
    private ApplicationUserRole roleName;


    public Role(ApplicationUserRole roleName) {
        this.roleName = roleName;
    }

    public Role() {
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


}


