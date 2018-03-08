package com.smalaca.messagesender.service;


import com.smalaca.messagesender.domain.User;
import com.smalaca.messagesender.domain.UserFactory;
import com.smalaca.messagesender.exceptions.inmemory.UserDoesntExistException;
import com.smalaca.messagesender.repository.inmemory.InMemoryUserRepository;
import com.smalaca.messagesender.domain.UserRepository;
import org.junit.Assert;
import org.junit.Test;

public class UserCrudTest {
    private InMemoryUserRepository userRepository = new InMemoryUserRepository();
    private UserCrud userCrud = new UserCrud(userRepository);

    @Test
    public void shouldNotCreateUserWithOnlyLogin() {
        String login = "login";

        UserDto userDto = new UserDto();

        userDto.setLogin(login);

        boolean isUserCreated = userCrud.createUser(userDto).isSuccess();

        Assert.assertFalse(isUserCreated);
    }

    @Test
    public void shouldCreateUserWithLoginAndEmail() {
        String login = "login";
        String email = "email";

        UserDto userDto = new UserDto();

        userDto.setLogin(login);
        userDto.setEmail(email);

        boolean isUserCreated = userCrud.createUser(userDto).isSuccess();

        Assert.assertTrue(isUserCreated);
    }

    @Test
    public void shouldCreateUserWithLoginAndTwitter() {
        String login = "login6";
        String twitter = "twitter";

        UserDto userDto = new UserDto();

        userDto.setLogin(login);
        userDto.setTwitter(twitter);

        boolean isUserCreated = userCrud.createUser(userDto).isSuccess();

        Assert.assertTrue(isUserCreated);
    }

    @Test
    public void shouldCreateUserWithLoginAndSlack() {
        String login = "login5";
        String slack = "Slack";

        UserDto userDto = new UserDto();

        userDto.setLogin(login);
        userDto.setSlack(slack);

        boolean isUserCreated = userCrud.createUser(userDto).isSuccess();

        Assert.assertTrue(isUserCreated);
    }

    @Test
    public void shouldCreateUserWithAllFields() {
        String login = "login1";
        String email = "email";
        String twitter = "twitter";
        String slack = "slack";

        UserDto userDto = new UserDto();

        userDto.setLogin(login);
        userDto.setEmail(email);
        userDto.setTwitter(twitter);
        userDto.setSlack(slack);

        boolean isUserCreated = userCrud.createUser(userDto).isSuccess();

        Assert.assertTrue(isUserCreated);
    }

    @Test
    public void shouldReturnFalseWhenTryToAddUserWithAlreadyExistingLogin() {
        String login = "login2";
        String email = "email";
        String twitter = "twitter";
        String slack = "slack";

        UserDto userDto = new UserDto();

        userDto.setLogin(login);
        userDto.setEmail(email);
        userDto.setTwitter(twitter);
        userDto.setSlack(slack);

        boolean isUserCreated = userCrud.createUser(userDto).isSuccess();

        String login1 = "login2";

        userDto = new UserDto();

        userDto.setLogin(login1);

        boolean isUserCreated1 = userCrud.createUser(userDto).isSuccess();

        Assert.assertTrue(isUserCreated);
        Assert.assertFalse(isUserCreated1);
    }

    @Test
    public void shouldReturnFalseWhenTryToAddUserWithEmptyLogin() {
        String login = "";

        UserDto userDto = new UserDto();
        userDto.setLogin(login);

        boolean isUserCreated = userCrud.createUser(userDto).isSuccess();

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

        boolean isUserCreated = userCrud.createUser(userDto).isSuccess();


        Assert.assertFalse(isUserCreated);
    }

    @Test
    public void shouldReturnTrueWhenTryToBlockNotBLockedUser() {
        String login = "login";
        String email = "email";
        String twitter = "twitter";
        String slack = "slack";

        UserDto userDto = new UserDto();

        userDto.setLogin(login);
        userDto.setEmail(email);
        userDto.setTwitter(twitter);
        userDto.setSlack(slack);

        userCrud.createUser(userDto);

        Assert.assertTrue(userCrud.blockUser(login).isSuccess());
    }

    @Test
    public void shouldReturnFalseWhenTryToBlockNonExistingUser() {
        Assert.assertFalse(userCrud.blockUser("login").isSuccess());
    }

    @Test
    public void shouldReturnFalseWhenTryToBlockUserPassingEmptyStringAsLogin() {
        Assert.assertFalse(userCrud.blockUser("").isSuccess());

    }

    @Test
    public void shouldReturnTrueWhenQueryForBlockedUser() {
        String login = "login";
        String email = "email";
        String twitter = "twitter";
        String slack = "slack";

        UserDto userDto = new UserDto();

        userDto.setLogin(login);
        userDto.setEmail(email);
        userDto.setTwitter(twitter);
        userDto.setSlack(slack);

        userCrud.createUser(userDto);
        userCrud.blockUser(login);

        Assert.assertTrue(userCrud.isUserBlocked(login));
    }

    @Test
    public void shouldReturnFalseWhenQueryForNonBlockedUser() {
        String login = "login";
        String email = "email";
        String twitter = "twitter";
        String slack = "slack";

        UserDto userDto = new UserDto();

        userDto.setLogin(login);
        userDto.setEmail(email);
        userDto.setTwitter(twitter);
        userDto.setSlack(slack);

        userCrud.createUser(userDto);

        Assert.assertFalse(userCrud.isUserBlocked(login));
    }

    @Test
    public void shouldUpdateUserIfExist() {
        UserDto userDto = new UserDto();
        userDto.setLogin("login");
        userDto.setEmail("email");

        userRepository.add(new UserFactory().createFrom(userDto));
        userDto.setEmail("emailNew");


        Assert.assertEquals(userCrud.updateUser(userDto).getMessage(), "User login updated");
    }

    @Test
    public void shouldNotupdateUserIfNoPresent() {
        UserDto userDto = new UserDto();
        userDto.setLogin("login");
        userDto.setEmail("email");
        Assert.assertEquals(userCrud.updateUser(userDto).getMessage(), "login - USER DOESN'T EXIST ,can't update.");
    }

    @Test
    public void AfterUpdateUserListOfAllUsersShouldStillHaveSize1() {
        UserDto userDto = new UserDto();
        userDto.setLogin("login");
        userDto.setEmail("email");

        userRepository.add(new UserFactory().createFrom(userDto));
        userDto.setEmail("emailNew");
        userCrud.updateUser(userDto);

        Assert.assertEquals(userCrud.showAllUsers().size(), 1);
    }

    @Test
    public void shouldShowUser() {
        UserDto userDto = new UserDto();
        userDto.setLogin("login");
        userDto.setEmail("email");
        userRepository.add(new UserFactory().createFrom(userDto));

        User user = userCrud.showUser(userDto.getLogin());
        Assert.assertEquals(user, new UserFactory().createFrom(userDto));
    }

    @Test
    public void showReturnNullIfUserNotPresent() {

        User user = userCrud.showUser("login");
        Assert.assertNull(user);
    }
}