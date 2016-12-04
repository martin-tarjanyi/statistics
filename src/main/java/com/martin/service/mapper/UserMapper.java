package com.martin.service.mapper;

import com.martin.api.model.User;
import com.martin.dataaccess.model.DBUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapper
{
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserMapper(PasswordEncoder passwordEncoder)
    {
        this.passwordEncoder = passwordEncoder;
    }

    public DBUser map(User user)
    {
        String encodedPassword = passwordEncoder.encode(String.valueOf(user.getPassword()));

        return new DBUser(user.getName(), encodedPassword);
    }
}
