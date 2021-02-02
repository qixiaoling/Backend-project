package nl.novi.Backend.service;

import nl.novi.Backend.model.AppUser;
import nl.novi.Backend.model.Role;
import nl.novi.Backend.payload.response.MessageResponse;
import nl.novi.Backend.repo.RoleRepository;
import nl.novi.Backend.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AppUser_Role_Service {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public AppUser_Role_Service(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public ResponseEntity<?> addAppUserWithRoles (AppUser appUser) {


        Optional<AppUser> foundUser = userRepository.findById(appUser.getUser_id());

        if (foundUser.isPresent()) {
            Set<Role> newRoles = new HashSet<>();
            for (Role role : appUser.getRoles()) {
                Role aRole = roleService.findRoleByRoleName(role.getRoleName());
                newRoles.add(aRole);
            }
            AppUser theUser = foundUser.get();
            theUser.setRoles(newRoles);
            userRepository.save(theUser);
            return ResponseEntity.ok().body(new MessageResponse("Roles are added to this AppUser"));
        }
        return ResponseEntity.badRequest().body("The user cannot be found!");
    }



        public List<AppUser> getAllAppUsers(){
            List<AppUser> appUsers = new ArrayList<>();
            userRepository.findAll().forEach(appUsers::add);
            return appUsers;
        }

    public ResponseEntity<?> addAppUsers(AppUser appUser) {
        if(Boolean.TRUE.equals(userRepository.existsByUserName(appUser.getUserName()))){
            return ResponseEntity.badRequest().body("The username is already exists.");
        }

        AppUser freshUser = new AppUser();
        freshUser.setUserName(appUser.getUserName());
        freshUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        freshUser.setEmail(appUser.getEmail());

        userRepository.save(freshUser);
        return ResponseEntity.ok().body("The new user is now added");
    }



}
