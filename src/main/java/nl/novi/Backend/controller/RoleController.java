package nl.novi.Backend.controller;

import nl.novi.Backend.model.Role;
import nl.novi.Backend.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Role> getAllRoles(){
        return roleService.getAllRoles();
    }

}
