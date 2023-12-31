package com.ugurukku.linkshortener.security;

import com.ugurukku.linkshortener.service.AuthBusinessService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class AuthBusinessServiceImpl implements AuthBusinessService {

    MyUserDetailsService userDetailsService;

    @Override
    public void setAuthentication(String email) {
        MyUserDetails userDetails = userDetailsService.loadUserByUsername(email);
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(userDetails,"",userDetails.getAuthorities())
        );
    }

}
