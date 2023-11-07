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
public class UserCheckingService extends ServiceMethods<User> implements UserCheckingServiceInterface {
    private UserRepository userRepository;

    @Override
    public User create(User user){
        user.setLogin();
        return super.create(user);
    }
    @Override
    public User getUserByLogin(String login) {
        Optional<User> optUser = this.userRepository.findByLogin(Login.create(login));
        return optUser.orElseThrow(()->new DataNotFounded("The user login provided does not match with users login stored in the database"));
    }

    @Autowired
    protected void setRepository(UserRepository userRepository){
        this.repository = userRepository;
        this.userRepository = userRepository;
    }
}
