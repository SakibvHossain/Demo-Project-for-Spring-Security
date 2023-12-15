package com.example.securityproject.service;

import com.example.securityproject.entity.User;
import com.example.securityproject.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

@Service
public class UserService {
    private final UserRepository repository;
    private final HashService hashService;

    public UserService(UserRepository repository, HashService hashService) {
        this.repository = repository;
        this.hashService = hashService;
    }

    public boolean isUsernameAvailable(String username){
        return repository.getUserByName(username) == null;
    }

    public boolean createUser(User user){
        if (repository.getUserByName(user.getUserName())!=null){
            return false;
        }

        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        String encodedSalt = Base64.getEncoder().encodeToString(salt);
        String hashedPassword = hashService.getHashedValue(user.getPassword(), encodedSalt);

        user.setSalt(encodedSalt);
        user.setPassword(hashedPassword);
        repository.save(user);
        return true;
    }
}
