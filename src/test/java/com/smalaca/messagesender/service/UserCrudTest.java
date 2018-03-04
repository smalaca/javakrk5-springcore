package com.smalaca.messagesender.service;


import com.smalaca.messagesender.repository.inmemory.InMemoryUserRepository;
import org.junit.Assert;
import org.junit.Test;

public class UserCrudTest {
    public static final String LOGIN = "login";
    public static final String EMAIL = "email";
    public static final String TWITTER = "twitter";
    public static final String SLACK = "slack";
    private InMemoryUserRepository userRepository = new InMemoryUserRepository();
    private UserCrud userCrud = new UserCrud(userRepository);

    @Test
    public void shouldNotCreateUserWithOnlyLogin() {
        String login = LOGIN;

        UserDto userDto = new UserDto();

        userDto.setLogin(login);

        boolean isUserCreated = userCrud.createUser(userDto);

        Assert.assertFalse(isUserCreated);
    }

    @Test
    public void shouldCreateUserWithLoginAndEmail() {
        String login = LOGIN;
        String email = EMAIL;

        UserDto userDto = new UserDto();

        userDto.setLogin(login);
        userDto.setEmail(email);

        boolean isUserCreated = userCrud.createUser(userDto);

        Assert.assertTrue(isUserCreated);
    }

    @Test
    public void shouldCreateUserWithLoginAndTwitter() {
        String login = "login6";
        String twitter = TWITTER;

        UserDto userDto = new UserDto();

        userDto.setLogin(login);
        userDto.setTwitter(twitter);

        boolean isUserCreated = userCrud.createUser(userDto);

        Assert.assertTrue(isUserCreated);
    }

    @Test
    public void shouldCreateUserWithLoginAndSlack() {
        String login = "login5";
        String slack = "Slack";

        UserDto userDto = new UserDto();

        userDto.setLogin(login);
        userDto.setSlack(slack);

        boolean isUserCreated = userCrud.createUser(userDto);

        Assert.assertTrue(isUserCreated);
    }

    @Test
    public void shouldCreateUserWithAllFields() {
        String login = "login1";
        String email = EMAIL;
        String twitter = TWITTER;
        String slack = SLACK;

        UserDto userDto = new UserDto();

        userDto.setLogin(login);
        userDto.setEmail(email);
        userDto.setTwitter(twitter);
        userDto.setSlack(slack);

        boolean isUserCreated = userCrud.createUser(userDto);

        Assert.assertTrue(isUserCreated);
    }

    @Test
    public void shouldReturnFalseWhenTryToAddUserWithAlreadyExistingLogin() {
        String login = "login2";
        String email = EMAIL;
        String twitter = TWITTER;
        String slack = SLACK;

        UserDto userDto = new UserDto();

        userDto.setLogin(login);
        userDto.setEmail(email);
        userDto.setTwitter(twitter);
        userDto.setSlack(slack);

        boolean isUserCreated = userCrud.createUser(userDto);

        String login1 = "login2";

        userDto = new UserDto();

        userDto.setLogin(login1);

        boolean isUserCreated1 = userCrud.createUser(userDto);

        Assert.assertTrue(isUserCreated);
        Assert.assertFalse(isUserCreated1);
    }

    @Test
    public void shouldReturnFalseWhenTryToAddUserWithEmptyLogin() {
        String login = "";

        UserDto userDto = new UserDto();
        userDto.setLogin(login);

        boolean isUserCreated = userCrud.createUser(userDto);

        Assert.assertFalse(isUserCreated);
    }

    @Test
    public void shouldReturnFalseWhenTryToAddUserWithAllFieldsExceptLogin() {
        String login = "";
        String email = "email3";
        String twitter = "twitter3";
        String slack = "slack3";

        UserDto userDto = new UserDto();

        userDto.setLogin(login);
        userDto.setEmail(email);
        userDto.setTwitter(twitter);
        userDto.setSlack(slack);

        boolean isUserCreated = userCrud.createUser(userDto);


        Assert.assertFalse(isUserCreated);
    }

    @Test
    public void shouldReturnTrueWhenTryToBlockNotBLockedUser(){
        UserDto userDto = new UserDto();
        userDto.setLogin(LOGIN);
        userDto.setEmail(EMAIL);
        userDto.setTwitter(TWITTER);
        userDto.setSlack(SLACK);

        userCrud.createUser(userDto);

        Assert.assertTrue(userCrud.blockUser(LOGIN));
    }

    @Test
    public void shouldReturnFalseWhenTryToBlockNonExistingUser(){
        Assert.assertFalse(userCrud.blockUser(LOGIN));
    }

    @Test
    public void shouldReturnFalseWhenTryToBlockUserPassingEmptyStringAsLogin(){
        Assert.assertFalse(userCrud.blockUser(""));

    }

    @Test
    public void shouldReturnTrueWhenQueryForBlockedUser(){
        UserDto userDto = new UserDto();

        userDto.setLogin(LOGIN);
        userDto.setEmail(EMAIL);
        userDto.setTwitter(TWITTER);
        userDto.setSlack(SLACK);

        userCrud.createUser(userDto);
        userCrud.blockUser(LOGIN);

        Assert.assertTrue(userCrud.isUserBlocked(LOGIN));
    }

    @Test
    public void shouldReturnFalseWhenQueryForNonBlockedUser(){
        UserDto userDto = new UserDto();

        userDto.setLogin(LOGIN);
        userDto.setEmail(EMAIL);
        userDto.setTwitter(TWITTER);
        userDto.setSlack(SLACK);
        userCrud.createUser(userDto);

        Assert.assertFalse(userCrud.isUserBlocked(LOGIN));
    }

    @Test
    public void shouldUpdateUser() {
        UserDto userDto = new UserDto();

        userDto.setLogin(LOGIN);
        userDto.setEmail(EMAIL);
        userDto.setTwitter(TWITTER);
        userDto.setSlack(SLACK);

        userCrud.createUser(userDto);
        userDto.setEmail("a@a.com");

        Assert.assertTrue(userCrud.updateUser(userDto));
    }

    @Test
    public void shouldNotUpdateUser() {
        UserDto userDto = new UserDto();

        userDto.setLogin(LOGIN);
        userDto.setEmail(EMAIL);
        userDto.setTwitter(TWITTER);
        userDto.setSlack(SLACK);

        userCrud.createUser(userDto);
        userDto.setEmail("");
        userDto.setSlack("");
        userDto.setTwitter("");

        Assert.assertFalse(userCrud.updateUser(userDto));
    }

    @Test
    public void shouldNotUpdateUserWhenNoChange() {
        UserDto userDto = new UserDto();

        userDto.setLogin(LOGIN);
        userDto.setEmail(EMAIL);
        userDto.setTwitter(TWITTER);
        userDto.setSlack(SLACK);

        userCrud.createUser(userDto);

        Assert.assertFalse(userCrud.updateUser(userDto));
    }

}