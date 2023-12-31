package com.ugurukku.linkshortener.security;

import com.ugurukku.linkshortener.model.entity.User;
import com.ugurukku.linkshortener.model.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findUserByEmail(username).orElseThrow(() -> new RuntimeException("User not found."));
        return new MyUserDetails(user);
    }
}
