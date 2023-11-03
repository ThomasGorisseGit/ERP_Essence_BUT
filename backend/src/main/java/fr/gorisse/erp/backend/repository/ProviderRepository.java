package fr.gorisse.erp.backend.repository;

import fr.gorisse.erp.backend.entity.Product;
import fr.gorisse.erp.backend.entity.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface ProviderRepository extends DefaultRepository<Provider> {

}
