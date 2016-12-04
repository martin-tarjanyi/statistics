package com.martin.service;

import com.martin.api.model.User;
import com.martin.dataaccess.model.DBUser;
import com.martin.dataaccess.repository.UserRepository;
import com.martin.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserRegistrationService
{
    private final UserRepository repository;
    private final UserMapper userMapper;

    @Autowired
    public UserRegistrationService(UserRepository repository, UserMapper userMapper)
    {
        this.repository = repository;
        this.userMapper = userMapper;
    }

    public void register(User user)
    {
        DBUser dbUser = userMapper.map(user);

        repository.save(dbUser);
    }

    public boolean userExistsWithUsername(User user)
    {
        DBUser registeredUser = repository.findByName(user.getName());

        return registeredUser != null;
    }
}
