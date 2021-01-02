package nl.novi.Backend.security_service;
import nl.novi.Backend.model.AppUser;
import nl.novi.Backend.security_config.ApplicationUserRole;
import nl.novi.Backend.security_repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

import java.util.Optional;

@Service("userDetailsSerivce")
public class ApplicationUserService implements UserDetailsService {
    /*@Autowired
    UserRepository userRepository;



    public ApplicationUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

   @Override
    public UserDetailsImpl loadUserByUsername(String userName) throws UsernameNotFoundException {
        AppUser appUser = userRepository.findAppUserByUserName(userName)
                .orElseThrow(()->new UsernameNotFoundException("User is not found."));
        return UserDetailsImpl.build(appUser);

        }*/

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        List<GrantedAuthority> auths = AuthorityUtils.commaSeparatedStringToAuthorityList("ADMIN");
        return new User("mary", new BCryptPasswordEncoder().encode("123"), auths);
    }
}
