package com.project.workshopmongo.services;

import com.project.workshopmongo.domain.User;
import com.project.workshopmongo.repository.UserRepository;
import com.project.workshopmongo.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findById(String id){
        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()){
            throw new ObjectNotFoundException("Objeto nao encontrado");
        }
        return user.get();
    }
}
