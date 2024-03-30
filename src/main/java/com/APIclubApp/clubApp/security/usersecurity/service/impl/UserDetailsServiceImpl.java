package com.APIclubApp.clubApp.security.usersecurity.service.impl;

import com.APIclubApp.clubApp.security.usersecurity.model.UserEntity;
import com.APIclubApp.clubApp.security.usersecurity.repository.UserSecurityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserSecurityRepository userSecurityRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userSecurityRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User "+ username + "does not exist."));

        Collection<? extends GrantedAuthority> authorities = userEntity.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority("ROLE".concat(role.getRolename().name())))
                .collect(Collectors.toSet());

        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;

        return new User(userEntity.getUsername(),
                userEntity.getPassword(),
                enabled, accountNonExpired, credentialsNonExpired, accountNonLocked,authorities);
    }
}