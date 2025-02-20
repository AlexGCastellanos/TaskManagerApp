package org.bgarzon.tasker_manager.services.implementations;

import org.bgarzon.tasker_manager.entities.User;
import org.bgarzon.tasker_manager.repositories.UserRepository;
import org.bgarzon.tasker_manager.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }



    
    





}
