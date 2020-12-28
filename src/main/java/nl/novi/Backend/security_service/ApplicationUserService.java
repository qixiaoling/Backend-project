package nl.novi.Backend.security_service;

import nl.novi.Backend.security_model.User;
import nl.novi.Backend.security_repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ApplicationUserService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    public ApplicationUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> userfromDB = userRepository.findByUserName(userName);
        userfromDB.orElseThrow(()->new UsernameNotFoundException("User is not found."));
        return userfromDB.map(ApplicationUserDetails::new).get();
    }
}
