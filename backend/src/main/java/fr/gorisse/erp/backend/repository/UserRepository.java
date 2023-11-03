package fr.gorisse.erp.backend.repository;

import fr.gorisse.erp.backend.entity.Product;
import fr.gorisse.erp.backend.entity.User;
import fr.gorisse.erp.backend.entity.valueObject.Login;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends DefaultRepository<User> {
    Optional<User> findByLogin(Login login);
}
