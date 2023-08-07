package com.example.minifacebookclone.services.servicesInterfaces;

import com.example.minifacebookclone.entities.FacebookUser;

import java.util.List;
import java.util.Optional;

public interface UserServices {
    FacebookUser registerUser(FacebookUser newUser);
    List<FacebookUser> getAllUsers();
    Optional<FacebookUser> getUserById(Long id);
    FacebookUser updateUser(FacebookUser user);
    Optional<FacebookUser> loginUser(String email, String password);
    void deleteUser(Long id);
    Long getUserIdByUsername(String name);

    //FacebookUser getUserByUsername(String username);
}
