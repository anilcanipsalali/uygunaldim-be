package com.uygunaldim.service;

import com.uygunaldim.exception.NotFoundException;
import com.uygunaldim.repository.UserRepository;
import com.uygunaldim.security.JwtUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return JwtUserDetails.create(userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("UYGNALDM-USER-404", "User could not found by username: " + username)));
    }

    public UserDetails loadUserById(Long id) {
        return JwtUserDetails.create(userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("UYGNALDM-USER-404", "User could not found by id: " + id)));
    }
}
