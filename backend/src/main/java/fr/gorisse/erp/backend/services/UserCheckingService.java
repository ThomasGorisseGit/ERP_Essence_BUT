package fr.gorisse.erp.backend.services;

import fr.gorisse.erp.backend.entity.User;
import fr.gorisse.erp.backend.entity.valueObject.Login;
import fr.gorisse.erp.backend.entity.valueObject.Status;
import fr.gorisse.erp.backend.exceptions.DataNotFounded;
import fr.gorisse.erp.backend.repository.UserRepository;
import fr.gorisse.erp.backend.services.interfaces.UserCheckingServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserCheckingService extends ServiceMethods<User> implements UserCheckingServiceInterface {
    private UserRepository userRepository;

    @Override
    public User create(User user){
        user.setLogin();
        if(user.getStatus()==null){
            user.setStatus(Status.create("Employee"));
            user.setSalary(Status.getSalary("Employee"));

        }
        if(user.getSalary() == 0){
            user.setSalary(Status.getSalary(user.getStatus().getUserStatus()));
        }


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
