package com.pricode.usersystem.service;

import com.pricode.usersystem.model.User;

import java.util.List;

public interface UserService {

    User createUser(User user);

    List<User> getUsers();

    Boolean deleteUser(Long id);

    User getUserById(Long id);

    User updateUser(Long id, User user);
}
