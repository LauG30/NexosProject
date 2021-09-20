package com.example.NexosProject.services;

import com.example.NexosProject.model.User;
import com.example.NexosProject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User create (User user){
        return userRepository.save(user);
    }

    public List<User> getAllUser (){
        return userRepository.findAll();
    }

    public void deleteUser (User user){
        userRepository.delete(user);
    }

    public Optional<User> findById(int id){
        return userRepository.findById(id);
    }

}
