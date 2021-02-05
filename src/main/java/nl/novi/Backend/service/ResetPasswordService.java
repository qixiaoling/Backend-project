package nl.novi.Backend.service;

import nl.novi.Backend.model.AppUser;
import nl.novi.Backend.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ResetPasswordService {

    @Autowired
    private UserRepository userRepository;

    public ResetPasswordService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseEntity<?> resetPassword(Long User_id, AppUser appUser) {
        Optional<AppUser> userDB = userRepository.findById(User_id);
        if (userDB.isPresent()) {
            userDB.get().setPassword(new BCryptPasswordEncoder().encode(appUser.getPassword()));
            userRepository.save(userDB.get());
            return ResponseEntity.ok().body("You password is now reset successfully.");
        }
        return ResponseEntity.badRequest().body("Error, please check your user id again.");
    }
}
