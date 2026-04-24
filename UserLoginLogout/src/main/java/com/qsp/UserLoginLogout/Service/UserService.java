package com.qsp.UserLoginLogout.Service;

import com.qsp.UserLoginLogout.Entity.User;
import com.qsp.UserLoginLogout.Repository.UserRepository;
import com.qsp.UserLoginLogout.exceptionhandling.EmptyInputException;
import com.qsp.UserLoginLogout.exceptionhandling.IdNotfoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User savedUser(User user) {
        if (user.getName() == null || user.getName().isEmpty()) {
            throw new EmptyInputException();
        }
        return userRepository.save(user);
    }

    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public Optional<User> getUserByid(int id) {
        Optional<User> ids = userRepository.findById(id);
        if (ids.isEmpty()) {
            throw new IdNotfoundException();
        }
        return ids;
    }

    public User updateByid(int id, User user) {
        User existing = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User Not Found"));
        existing.setName(user.getName());
        existing.setPhone(user.getPhone());
        existing.setEmail(user.getEmail());
        existing.setFileUrl(user.getFileUrl());
        existing.setStatus(user.getStatus());
        return userRepository.save(existing);
    }

    public Optional<User> deleteByid(int id) {
        Optional<User> user = getUserByid(id);
        if (user.isEmpty()) {
            throw new IdNotfoundException();
        }
        userRepository.deleteById(id);
        return user;
    }
}