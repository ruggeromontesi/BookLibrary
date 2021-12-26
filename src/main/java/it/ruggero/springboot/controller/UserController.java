package it.ruggero.springboot.controller;

import it.ruggero.springboot.data.entity.User;
import it.ruggero.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping("/ruggero/users")
    public List<User> getAllUsers( ) {
        return userService.getAllUsers();
    }

    @GetMapping(value = "/ruggero/users/{id}")
    public User getUserById(@PathVariable("id") int id) {
        return userService.findById(id);
    }

    @GetMapping(value = "/ruggero/customized")
    public List<User> getUserById() {
        return userService.findRuggero();
    }

    @PostMapping ("/ruggero/users")
    public User addUser(@RequestBody User user) {
        return userService.add(user);
    }



}
