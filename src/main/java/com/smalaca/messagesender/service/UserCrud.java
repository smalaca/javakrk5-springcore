package com.smalaca.messagesender.service;

import com.smalaca.messagesender.domain.User;
import com.smalaca.messagesender.domain.UserFactory;
import com.smalaca.messagesender.domain.UserRepository;
import com.smalaca.messagesender.exceptions.inmemory.UserAlreadyExistException;
import com.smalaca.messagesender.exceptions.inmemory.UserDoesntExistException;
import com.smalaca.messagesender.repository.inmemory.InMemoryMessageRepository;
import com.smalaca.messagesender.repository.inmemory.InMemoryUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.smalaca.messagesender.service.Response.aFailureResponse;
import static com.smalaca.messagesender.service.Response.aSuccessfulResponse;
import static com.smalaca.messagesender.service.Response.aSuccessfulResponseWith;

@Service
public class UserCrud {

    private final UserRepository userRepository;

    @Autowired
    public UserCrud(InMemoryUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Response createUser(UserDto userDto) {
        User user = new UserFactory().createFrom(userDto);

        if (isUserValid(user)) {
            try {
                userRepository.add(user);
                return aSuccessfulResponseWith("User Created");
            } catch (UserAlreadyExistException e) {
                return aFailureResponse("User Creation Failed!");
            }
        } else
            return aFailureResponse("Invalid data passed");
    }

    public Response blockUser(String login) {
        try {
            userRepository.blockUser(login);
            return aSuccessfulResponseWith("User Blocked");
        } catch (UserDoesntExistException e) {
            return aFailureResponse(e.getMessage());
        }
    }

    public boolean isUserBlocked(String login) {
        return userRepository.getUserByLogin(login).isBlocked();
    }

    private boolean isUserValid(User user) {
        return (!((user.getLogin().equals("")) || user.getEmail().equals("") && user.getTwitter().equals("") && user.getSlack().equals("")));
    }

    public List<User> showAllUsers() {
        return userRepository.showAllUsers();
    }

    public Response updateUser(UserDto userDto) {
        User user;

        try {
            user = userRepository.getUserByLogin(userDto.getLogin());
        } catch (UserDoesntExistException e) {
            return aFailureResponse(e.getMessage() + " ,can't update.");
        }

        userRepository.updateUser(user, userDto);
        return aSuccessfulResponseWith("User " + user.getLogin() + " updated");
    }

    public List<User> showUser(String login) {
        List<User> userToShow = new ArrayList<>();

        try {
            userToShow.add(userRepository.getUserByLogin(login));
        } catch (UserDoesntExistException e) {
            return userToShow;
        }
        return userToShow;

    }
}