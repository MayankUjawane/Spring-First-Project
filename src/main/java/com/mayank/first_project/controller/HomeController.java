package com.mayank.first_project.controller;

import com.mayank.first_project.model.User;
import com.mayank.first_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HomeController {

    @Autowired
    UserService userService;

    @GetMapping({"/", "/home"})
    public String home() {
        return "<h1>Hello<h1>";
    }

    @GetMapping("/user/all")
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {

        if (userService.findById(id).isPresent()) {
            return new ResponseEntity(userService.findById(id).get(), HttpStatus.OK);
        }
        return ResponseEntity.notFound().header("Message", "Not Found").build();
    }

    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
    }

    // Put is used for update
    @PutMapping("/user/update/{id}")
    public void updateUser(@RequestBody User user , @PathVariable Long id) {
        userService.update(user, id);
    }

    // Post is used to create
    @PostMapping("/user/add")
    public void addUser(@RequestBody User user) {
        userService.save(user);
    }

}
