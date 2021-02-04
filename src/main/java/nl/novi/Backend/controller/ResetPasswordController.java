package nl.novi.Backend.controller;

import nl.novi.Backend.model.AppUser;
import nl.novi.Backend.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/resetpassword")
public class ResetPasswordController {

    @Autowired
    private UserRepository userRepository;
    @PostMapping("/{userid}")
    public ResponseEntity<?> resetPassword(@PathVariable("userid") Long User_id, @RequestBody AppUser appUser) {
        Optional<AppUser> userDB = userRepository.findById(User_id);
        if (userDB.isPresent()) {
            userDB.get().setPassword(new BCryptPasswordEncoder().encode(appUser.getPassword()));
            userRepository.save(userDB.get());
            return ResponseEntity.ok().body("You password is now reset successfully.");
        }
        return ResponseEntity.badRequest().body("Error, please check your user id again.");
    }
}
