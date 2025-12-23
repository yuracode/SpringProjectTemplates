package com.example.demo.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.ProjectUser;
import com.example.demo.mapper.ProjectUserMapper;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private ProjectUserMapper mapper;

    //こちらのServiceが稼働するとSecurityConfigの
    //public InMemoryUserDetailsManager userDetailsService()
    //は無効になる。
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ProjectUser u = mapper.findByUsername(username);
        if (u == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }
        List<GrantedAuthority> authorities = Arrays.stream((u.getRoles() == null ? "" : u.getRoles()).split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        return org.springframework.security.core.userdetails.User.withUsername(u.getUsername())
                .password(u.getPassword())
                .authorities(authorities)
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(!u.isEnabled())
                .build();
    }

}
