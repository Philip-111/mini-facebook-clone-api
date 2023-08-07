package com.example.minifacebookclone.services;

import com.example.minifacebookclone.entities.FacebookUser;
import com.example.minifacebookclone.repositories.UserRepository;
import com.example.minifacebookclone.services.servicesInterfaces.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UserServicesImpl implements UserServices {
    private final UserRepository userRepository;

    @Autowired
    public UserServicesImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public FacebookUser registerUser(FacebookUser newUser) {
        if (userRepository.existsByEmail(newUser.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }
        String genderValue = newUser.getGender().toString();
        newUser.setGender(genderValue);
        return userRepository.save(newUser);
    }

    @Override
    public List<FacebookUser> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<FacebookUser> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public FacebookUser updateUser(FacebookUser user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Optional<FacebookUser> loginUser(String email, String password) {

        Optional<FacebookUser> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            FacebookUser user = optionalUser.get();
            if (password.equals(user.getPassword())) {
                return optionalUser;
            }
        }
        return Optional.empty();
    }
    @Override
    public Long getUserIdByUsername(String username) {
        FacebookUser user = userRepository.findByName(username);
        if (user != null) {
            return user.getId();
        }
        return null;
    }
}
