package fr.gorisse.erp.backend.services.interfaces;

import fr.gorisse.erp.backend.entity.User;

import java.util.ArrayList;

public interface UserCheckingServiceInterface {
    User getUserByLogin(String login);

}
