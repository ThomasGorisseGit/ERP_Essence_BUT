package fr.gorisse.erp.backend.services;

import fr.gorisse.erp.backend.entity.Client;
import fr.gorisse.erp.backend.entity.ClientOrder;
import fr.gorisse.erp.backend.exceptions.DataNotFounded;
import fr.gorisse.erp.backend.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientOrderService extends ServiceMethods<ClientOrder> {
    @Autowired
    private OrderRepository orderRepository;
    @Override
    @Autowired
    protected void setRepository() {
        super.repository = orderRepository;
    }

    @Override
    public ClientOrder create(ClientOrder entity){
        entity.setInProgress(true);
        Optional<ClientOrder> cli = this.orderRepository.findByClient_IdAndInProgressEquals(entity.getClient().getId(),true);
        if(cli.isPresent() ){
            cli.get().setInProgress(false);
            super.edit(cli.get());
        }
        return super.create(entity);
    }

    public ClientOrder getClientOrder(int id){
        return this.orderRepository.findByClient_Id(id).orElseThrow(()->new DataNotFounded("Client doesn't have any orders"));
    }
}
