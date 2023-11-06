package fr.gorisse.erp.backend.repository;

import fr.gorisse.erp.backend.entity.ClientOrder;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface OrderRepository extends DefaultRepository<ClientOrder>{
    Optional<ClientOrder> findByClient_Id(int client_id);
    Optional<ClientOrder> findByClient_IdAndInProgressEquals(int client_id,boolean isInProgress);

}
