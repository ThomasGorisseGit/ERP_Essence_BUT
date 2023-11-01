package fr.gorisse.erp.backend.services.interfaces;

import fr.gorisse.erp.backend.entity.User;

import java.util.ArrayList;

public interface UserCheckingServiceInterface {
    User create (User user);
    ArrayList<User> getUsers();
    User getUserById(int id);
    User getUserByLogin(String login);
    int getNumberOfUsers();

}
