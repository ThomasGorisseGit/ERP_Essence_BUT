package fr.gorisse.erp.backend.services;

import fr.gorisse.erp.backend.entity.User;
import fr.gorisse.erp.backend.entity.valueObject.Login;
import fr.gorisse.erp.backend.exceptions.DataNotFounded;
import fr.gorisse.erp.backend.exceptions.InvalidInput;
import fr.gorisse.erp.backend.repository.UserRepository;
import fr.gorisse.erp.backend.services.interfaces.UserCheckingServiceInterface;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserCheckingService implements UserCheckingServiceInterface {
    @Autowired
    private UserRepository userRepository;

    public User create(User user){
        return this.userRepository.save(user);
    }

    @Override
    public ArrayList<User> getUsers() {
        return (ArrayList<User>) this.userRepository.findAll();
    }

    @Override
    public User getUserById(int user_id) {
        Optional<User> optUser = this.userRepository.findById(user_id);
        return optUser.orElseThrow(()->new DataNotFounded("The user id provided does not match with users id stored in the database"));
    }

    @Override
    public User getUserByLogin(String login) {
        Optional<User> optUser = this.userRepository.findByLogin(Login.create(login));
        return optUser.orElseThrow(()->new DataNotFounded("The user login provided does not match with users login stored in the database"));
    }

    @Override
    public int getNumberOfUsers() {
        return this.userRepository.findAll().size();
    }


}
