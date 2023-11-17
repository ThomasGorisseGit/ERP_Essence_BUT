package fr.gorisse.erp.backend.repository;

import fr.gorisse.erp.backend.entity.Stock;

import java.util.List;

public interface StockRepository extends DefaultRepository<Stock>{
    List<Stock> findStockByQuantityGreaterThan(long quantity);
}
