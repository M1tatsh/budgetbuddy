package com.mitatsh.testapp.security;

import com.mitatsh.testapp.domain.entities.User;
import com.mitatsh.testapp.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Service for loading user data into Spring Security's formatting.
 */
@RequiredArgsConstructor
public class BudgetUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
        return new BudgetUserDetails(user);
    }
}
