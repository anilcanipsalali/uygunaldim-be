package com.uygunaldim.service;

import com.uygunaldim.data.entity.User;
import com.uygunaldim.security.JwtUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return JwtUserDetails.create(userService.findUserByUsername(username));
    }

    public UserDetails loadUserById(Long id) {
        return JwtUserDetails.create(User.of(userService.getUserById(id)));
    }
}
