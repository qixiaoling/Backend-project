package nl.novi.Backend.security_service;

import nl.novi.Backend.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/securityManagement")
public class RoleController {
    @Autowired
    private RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/roles")
    public List<Role> getAllRoles(){
        return roleService.getAllRoles();
    }
    @PostMapping("/roles")
    public List<Role> addRoles(@RequestBody Role role){
        return roleService.addRole(role);
    }
}
