package nl.novi.Backend.security_config;
import nl.novi.Backend.model.AppUser;
import nl.novi.Backend.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetailsImpl loadUserByUsername(String userName) throws UsernameNotFoundException {
        AppUser userDB = userRepository.findAppUserByUserName(userName)
                .orElseThrow(() -> new UsernameNotFoundException("User is not found."));
        return new UserDetailsImpl(userDB);

    }

}
