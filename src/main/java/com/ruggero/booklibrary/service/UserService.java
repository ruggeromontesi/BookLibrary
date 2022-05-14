package com.ruggero.booklibrary.service;

import java.util.List;

import com.ruggero.booklibrary.entities.BookLibraryUser;
import com.ruggero.booklibrary.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

   private UserRepository userRepository = UserRepository.getInstance();

   public List<BookLibraryUser> getAllUsers() {
      return userRepository.getBookLibraryUserList();
   }
}
