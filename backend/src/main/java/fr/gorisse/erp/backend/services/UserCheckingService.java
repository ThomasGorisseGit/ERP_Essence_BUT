package fr.gorisse.erp.backend.services;

import fr.gorisse.erp.backend.entity.User;
import fr.gorisse.erp.backend.entity.valueObject.Login;
import fr.gorisse.erp.backend.exceptions.DataNotFounded;
import fr.gorisse.erp.backend.repository.ProviderRepository;
import fr.gorisse.erp.backend.repository.UserRepository;
import fr.gorisse.erp.backend.services.interfaces.ServiceMethodsInterface;
import fr.gorisse.erp.backend.services.interfaces.UserCheckingServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserCheckingService implements UserCheckingServiceInterface {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUserByLogin(String login) {
        Optional<User> optUser = this.userRepository.findByLogin(Login.create(login));
        return optUser.orElseThrow(()->new DataNotFounded("The user login provided does not match with users login stored in the database"));
    }


    public User create(User entity) {
        return this.userRepository.save(entity);
    }

    public User edit(User entity) {
        return this.userRepository.save(entity);
    }

    public void delete(User entity) {
        this.userRepository.delete(entity);
    }

    public List<User> getAll() {
        return this.userRepository.findAll();
    }

    public User getEntityById(int entity_id) {
       return this.userRepository.findById(entity_id).orElseThrow(()->new DataNotFounded(""));
    }

    public int getNumberOfEntity() {
        return this.userRepository.findAll().size();
    }
}
