package fr.gorisse.erp.backend.repository;

import fr.gorisse.erp.backend.entity.Delivery;

import java.util.Date;
import java.util.List;

public interface DeliveryRepository extends DefaultRepository<Delivery> {
    List<Delivery> findDeliveriesByDateAndState(Date date,String state);
    List<Delivery> findDeliveriesByDateBefore(Date date);
    List<Delivery> findAllByOrderByDateDesc();

}

