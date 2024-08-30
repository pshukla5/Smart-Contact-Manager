package com.scm.scm2_0.Services;

import java.util.List;
import java.util.Optional;

import com.scm.scm2_0.Entities.User;

public interface UserService {

    User saveUser(User user);
    Optional<User> getUserById(String id);
    Optional<User> updateUser(User user);
    void deleteUser(String id);
    Boolean isUserExist(String id);
    Boolean isUserExistByEmail(String email);

    List<User> getAllUsers();

    User findUserByEmail(String email);

    // add more methods related to user service

}
