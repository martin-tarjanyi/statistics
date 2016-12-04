package com.martin.service;

import java.util.Collections;

import com.martin.dataaccess.model.DBUser;
import com.martin.dataaccess.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserLoginService implements UserDetailsService
{
    private final UserRepository repository;

    @Autowired
    public UserLoginService(UserRepository repository)
    {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        DBUser registeredUser = repository.findByName(username);

        if (registeredUser == null)
        {
            throw new UsernameNotFoundException("Username or password is incorrect");
        }

        return new org.springframework.security.core.userdetails.User(registeredUser.getName(),
                registeredUser.getPassword(), Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
    }
}
