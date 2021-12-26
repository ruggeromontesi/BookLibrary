package it.ruggero.springboot.service;

import it.ruggero.springboot.data.entity.User;
import it.ruggero.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User add(User user) {
        return  userRepository.save(user);
    }

    public User findById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public List<User> getAllUsers( ) {
        return  userRepository.findAll();
    }

    public List<User> findRuggero( ) {
        return  userRepository.findUser("ruggero" );
    }

}
