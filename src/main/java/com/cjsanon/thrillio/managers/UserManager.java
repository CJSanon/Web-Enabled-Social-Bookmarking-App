package com.cjsanon.thrillio.managers;

import com.cjsanon.thrillio.constants.Gender;
import com.cjsanon.thrillio.constants.UserType;
import com.cjsanon.thrillio.dao.UserDao;
import com.cjsanon.thrillio.entities.User;

import java.util.List;

public class UserManager {
    private static final UserManager instance = new UserManager();
    private static final UserDao dao = new UserDao();
    private UserManager() {}

    public static UserManager getInstance() {
        return instance;
    } //creating the single instance of UserManager

    public User createUser(long id, String email, String password, String firstName, String lastName, Gender gender, UserType userType){
        User user = new User();
        user.setId(id);
        user.setEmail(email);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setGender(gender);
        user.setUserType(userType);

        return user;
    }

    //Relays the call to get users
    public List<User> getUsers() {
        return dao.getUsers();
    }

    public User getUser(long userId) {
        return dao.getUser(userId);
    }
}
