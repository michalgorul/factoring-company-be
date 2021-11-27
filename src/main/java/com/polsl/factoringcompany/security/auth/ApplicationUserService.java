package com.polsl.factoringcompany.security.auth;

import com.polsl.factoringcompany.user.UserEntity;
import com.polsl.factoringcompany.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static com.polsl.factoringcompany.security.ApplicationUserRole.ADMIN;

@Service
@AllArgsConstructor
public class ApplicationUserService implements UserDetailsService {

    private final UserRepository userRepository;

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
