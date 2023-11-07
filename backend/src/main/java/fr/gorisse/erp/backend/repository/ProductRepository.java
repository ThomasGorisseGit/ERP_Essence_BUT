package fr.gorisse.erp.backend.repository;

import fr.gorisse.erp.backend.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends DefaultRepository<Product> {
    List<Product> findByProviderId(int provider_id);
}
