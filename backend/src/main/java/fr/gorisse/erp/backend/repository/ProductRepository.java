package fr.gorisse.erp.backend.repository;

import fr.gorisse.erp.backend.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer> {
}
