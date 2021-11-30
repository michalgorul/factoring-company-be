package com.polsl.factoringcompany.security.auth;

import com.polsl.factoringcompany.user.UserEntity;
import com.polsl.factoringcompany.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static com.polsl.factoringcompany.security.ApplicationUserRole.ADMIN;

/**
 * The type Application user service.
 * <a href="https://docs.spring.io/spring-security/site/docs/current/api/org/springframework/security/core/userdetails/UserDetailsService.html">See more</a>
 * @author Michal Goral
 * @version 1.0
 */
@Service
@AllArgsConstructor
public class ApplicationUserService implements UserDetailsService {

    /**
     * the user repository bean
     */
    private final UserRepository userRepository;

    /**
     * Loads user by username. Finds user by username in database and creates new instance of application user class
     * @param username the username
     * @return the application user
     * @throws UsernameNotFoundException the UsernameNotFoundException exception
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Username %s not found", username)));

        return new ApplicationUser(
                ADMIN.getGrantedAuthorities(),
                userEntity.getUsername(),
                userEntity.getPassword(),
                true,
                !userEntity.isLocked(),
                true,
                userEntity.isEnabled()
        );
    }
}
