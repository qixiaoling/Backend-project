package nl.novi.Backend.security_service;

import nl.novi.Backend.model.AppUser;
import nl.novi.Backend.model.Customer;
import nl.novi.Backend.model.Role;
import nl.novi.Backend.payload.response.MessageResponse;
import nl.novi.Backend.security_repo.RoleRepository;
import nl.novi.Backend.security_repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppUser_Role_Service {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private RoleService roleService;

    public AppUser_Role_Service(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public ResponseEntity<?> addAppUserWithRoles (AppUser appUser) {
        AppUser newAppUser = new AppUser();
        newAppUser.setUser_id(appUser.getUser_id());
        newAppUser.getRoles().addAll(
                appUser.getRoles().stream().map(r -> {
                    Role rr = roleService.findRoleByRoleName(r.getRoleName());
                    rr.getAppUsers().add(newAppUser);
                    return rr;
                }).collect(Collectors.toList()));
        userRepository.save(newAppUser);
        return ResponseEntity.ok().body(new MessageResponse("Roles are added to this AppUser"));
    }

        public List<AppUser> getAllAppUsers(){
            List<AppUser> appUsers = new ArrayList<>();
            userRepository.findAll().forEach(appUsers::add);
            return appUsers;
        }

    public List<AppUser> addAppUsers(AppUser appUser) {
        List<AppUser> appUsers = new ArrayList<>();
        userRepository.save(appUser);
        return appUsers;
    }



}
