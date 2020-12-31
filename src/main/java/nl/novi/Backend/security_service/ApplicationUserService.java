package nl.novi.Backend.security_service;
import nl.novi.Backend.model.AppUser;
import nl.novi.Backend.security_repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("applicationUserService")
public class ApplicationUserService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;


    public ApplicationUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetailsImpl loadUserByUsername(String userName) throws UsernameNotFoundException {
        AppUser appUser = userRepository.findByUserName(userName)
                .orElseThrow(()->new UsernameNotFoundException("User is not found."));
        return UserDetailsImpl.build(appUser);

        }


}
