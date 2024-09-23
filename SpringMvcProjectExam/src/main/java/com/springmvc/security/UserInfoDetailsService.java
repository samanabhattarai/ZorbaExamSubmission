package com.springmvc.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserInfoDetailsService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername (String s) throws UsernameNotFoundException {

        // TODO: Implement check on database
        return null;
    }
}
