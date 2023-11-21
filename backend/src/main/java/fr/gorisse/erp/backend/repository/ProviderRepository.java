package fr.gorisse.erp.backend.repository;

import fr.gorisse.erp.backend.entity.Provider;

import java.util.List;

public interface ProviderRepository extends DefaultRepository<Provider> {
    Provider findByProductList_Id(int productId);
    List<Provider> findByProductListIsNotNullAndProductListNotEmptyAndProductList_StockIsNotNullAndProductList_Stock_DeliveryListIsNotNull();

}
