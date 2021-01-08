package nl.novi.Backend.controller;

import nl.novi.Backend.model.AppUser;
import nl.novi.Backend.service.AppUser_Role_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/securityManagement")
public class AppUser_Role_Controller {
    @Autowired
    private AppUser_Role_Service appUser_role_service;

    public AppUser_Role_Controller(AppUser_Role_Service appUser_role_service) {
        this.appUser_role_service = appUser_role_service;
    }
    @PostMapping("/appuser/addroles")
    public ResponseEntity<?> addAppUsersWithRoles(@RequestBody AppUser appUser){
        return appUser_role_service.addAppUserWithRoles(appUser);
    }
    @GetMapping("/appuser")
    public List<AppUser> getAllAppUsers(){
        return appUser_role_service.getAllAppUsers();
    }
    @PostMapping("/appusers")
    public List<AppUser> addAppUsers (@RequestBody AppUser appUser){
        return appUser_role_service.addAppUsers(appUser);
    }
}
