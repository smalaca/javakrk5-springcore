package com.smalaca.messagesender.service;

import com.smalaca.messagesender.domain.User;
import com.smalaca.messagesender.domain.UserFactory;
import com.smalaca.messagesender.domain.UserRepository;
import com.smalaca.messagesender.exceptions.inmemory.UserAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCrud {

    private final UserRepository userRepository;

    @Autowired
    public UserCrud(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean createUser(UserDto userDto) {
        User user = new UserFactory().createFrom(userDto);

        if ((user.getLogin().equals("")) || user.getEmail().equals("") && user.getTwitter().equals("") && user.getSlack().equals(""))
            return false;

        try {
            userRepository.add(user);
            return true;
        } catch (UserAlreadyExistException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }


}