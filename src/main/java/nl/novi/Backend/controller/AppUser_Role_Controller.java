package nl.novi.Backend.controller;

import nl.novi.Backend.model.AppUser;
import nl.novi.Backend.model.Role;
import nl.novi.Backend.service.AppUser_Role_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.Set;

@RestController
@RequestMapping("/securityManagement")
public class AppUser_Role_Controller {
    @Autowired
    private AppUser_Role_Service appUser_role_service;

    public AppUser_Role_Controller(AppUser_Role_Service appUser_role_service) {
        this.appUser_role_service = appUser_role_service;
    }
    @PostMapping("/appusers/addroles/{userid}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> addAppUsersWithRoles(@PathVariable ("userid") Long User_id, @RequestBody Set<Role> givenRoles){
        return appUser_role_service.addAppUserWithRoles(User_id, givenRoles);
    }
    @GetMapping("/appusers")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> getAllAppUsers(){

        return appUser_role_service.getAllAppUsers();
    }
    @PostMapping("/appusers")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> addAppUsers (@RequestBody AppUser appUser){
        return appUser_role_service.addAppUsers(appUser);
    }
    @PutMapping("/appusers/update/{userid}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> updateAppUsers(@PathVariable("userid") Long User_id, @RequestBody AppUser appUser){
        return appUser_role_service.updateAppUserById(User_id, appUser);
    }


}
