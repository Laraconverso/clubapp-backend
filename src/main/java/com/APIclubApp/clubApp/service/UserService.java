package com.APIclubApp.clubApp.service;

import com.APIclubApp.clubApp.model.User;

import java.util.List;

public interface UserService {

    public List<User> listAllUsers();

    User saveUser(User user);
    User getUserById(Long id);
    User updateUser(User user);
    void deleteUser(Long id);

}
