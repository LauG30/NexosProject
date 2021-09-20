package com.example.NexosProject.controller;

import com.example.NexosProject.model.User;
import com.example.NexosProject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(path = "/create/user")
    private ResponseEntity<User> saveUser (@RequestBody User user){
        User userId = userService.create(user);

        try{
            return ResponseEntity.created(new URI("/user/create/user" + userId.getId())).body(userId);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping(path = "/list/user")
    private ResponseEntity<List<User>> listAllUser (){
        return ResponseEntity.ok(userService.getAllUser());
    }

    @DeleteMapping(path = "/delete/user")
    private ResponseEntity<Void> deleteUser (@RequestBody User user){
        userService.deleteUser(user);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/get/user/{id}")
    private ResponseEntity<Optional<User>> getOneUser (@PathVariable(name = "id") int id){
        return ResponseEntity.ok(userService.findById(id));
    }
}
