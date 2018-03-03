package com.smalaca.messagesender.service;

import com.smalaca.messagesender.domain.User;
import com.smalaca.messagesender.domain.UserFactory;
import com.smalaca.messagesender.repository.inmemory.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCrud {

    @Autowired
    UserRepository userRepository;

    public boolean createUser(UserDto userDto) {
        User user = new UserFactory().createFrom(userDto);

        try{
            userRepository.add(user);
            return true;
        }catch(IllegalArgumentException e){
            return false;
        }
    }
}
