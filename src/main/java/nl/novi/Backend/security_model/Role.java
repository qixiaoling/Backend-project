package nl.novi.Backend.security_model;

import net.bytebuddy.dynamic.loading.InjectionClassLoader;
import nl.novi.Backend.security_config.ApplicationUserRole;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long role_id;
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


