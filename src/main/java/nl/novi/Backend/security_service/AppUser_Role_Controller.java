package nl.novi.Backend.security_service;

import nl.novi.Backend.model.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.awt.*;

@RestController
@RequestMapping("/securityManagement")
public class AppUser_Role_Controller {
    @Autowired
    private AppUser_Role_Service appUser_role_service;

    public AppUser_Role_Controller(AppUser_Role_Service appUser_role_service) {
        this.appUser_role_service = appUser_role_service;
    }
    @PostMapping("/appuser")
    public ResponseEntity<?> addAppUsersWithRoles(@RequestBody AppUser appUser){
        return appUser_role_service.addAppUserWithRoles(appUser);
    }
    @GetMapping("/appuser")
    public List<AppUser> getAllAppUsers(){
        return appUser_role_service.getAllAppUsers();
    }
}
