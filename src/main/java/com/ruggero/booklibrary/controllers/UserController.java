package com.ruggero.booklibrary.controllers;

import java.util.List;

import com.ruggero.booklibrary.entities.BookLibraryUser;
import com.ruggero.booklibrary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

   private final UserService userService;

   @Autowired
   public UserController(UserService userService) {
      this.userService = userService;
   }

   /** Rest API endpoint to  get all library users.
    * @return List<@BookLibraryUser> all users
    **/
   @GetMapping("/users")
   public List<BookLibraryUser> getAllLibraryUser() {
      return userService.getAllUsers();
   }
}
