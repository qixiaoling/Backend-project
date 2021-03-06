package nl.novi.Backend.service;

import nl.novi.Backend.model.Role;
import nl.novi.Backend.security_config.ApplicationUserRole;
import nl.novi.Backend.repo.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public ResponseEntity<?> getAllRoles(){
        List<Role> roles = new ArrayList<>();
        roleRepository.findAll().forEach(roles::add);
        return ResponseEntity.ok().body(roles);
    }
    public Role findRoleByRoleName(ApplicationUserRole roleName){

        return roleRepository.findByRoleName(roleName);
    }
    public List<Role> addRole(Role role) {
        List<Role> roles = new ArrayList<>();
        roleRepository.save(role);
        return roles;
    }
}
