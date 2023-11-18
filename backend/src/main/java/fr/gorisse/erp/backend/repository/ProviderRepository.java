package fr.gorisse.erp.backend.repository;

import fr.gorisse.erp.backend.entity.Provider;

public interface ProviderRepository extends DefaultRepository<Provider> {
    Provider findByProductList_Id(int productId);


}
