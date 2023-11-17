package fr.gorisse.erp.backend.repository;

import fr.gorisse.erp.backend.entity.Product;

import java.util.List;

public interface ProductRepository extends DefaultRepository<Product> {
    List<Product> findByProviderId(int provider_id);
    List<Product> findByProviderIsNull();
    List<Product> findByProviderIsNotNull();

}
