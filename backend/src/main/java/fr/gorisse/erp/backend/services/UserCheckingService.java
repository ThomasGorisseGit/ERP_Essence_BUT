package fr.gorisse.erp.backend.services;

import fr.gorisse.erp.backend.entity.User;
import fr.gorisse.erp.backend.entity.valueObject.Login;
import fr.gorisse.erp.backend.entity.valueObject.Password;
import fr.gorisse.erp.backend.entity.valueObject.Status;
import fr.gorisse.erp.backend.exceptions.DataNotFounded;
import fr.gorisse.erp.backend.repository.UserRepository;
import fr.gorisse.erp.backend.services.interfaces.UserCheckingServiceInterface;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@NoArgsConstructor
public class UserCheckingService extends ServiceMethods<User> implements UserCheckingServiceInterface , UserDetailsService {
    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    public User create(User user){


        user.setLogin();
        user.setPassword(Password.create(this.passwordEncoder().encode(user.getPassword())));
        if(this.userRepository.findByLogin(user.getLogin()).isPresent()){
            throw new RuntimeException("" +
                    "\n#########################################\n" +
                    "########## User already exists ##########\n" +
                    "#########################################\n");
        }



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


    /*
    Security :
    To access user data, we need an object type UserDetailService
    It allows the security to get user data.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByLogin(Login.create(username)).orElseThrow(() -> new UsernameNotFoundException("User not founded"));
    }
}
