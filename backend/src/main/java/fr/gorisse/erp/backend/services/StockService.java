package fr.gorisse.erp.backend.services;

import fr.gorisse.erp.backend.entity.Stock;
import fr.gorisse.erp.backend.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockService extends ServiceMethods<Stock>{
    @Autowired
    private StockRepository stockRepository;
    @Autowired
    @Override
    protected void setRepository() {
        super.repository = stockRepository;
    }


}
