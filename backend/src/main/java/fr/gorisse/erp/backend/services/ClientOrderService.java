package fr.gorisse.erp.backend.services;

import fr.gorisse.erp.backend.entity.ClientOrder;
import fr.gorisse.erp.backend.entity.OrderList;
import fr.gorisse.erp.backend.exceptions.DataNotFounded;
import fr.gorisse.erp.backend.repository.DefaultRepository;
import fr.gorisse.erp.backend.repository.OrderListRepository;
import fr.gorisse.erp.backend.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientOrderService extends ServiceMethods<ClientOrder> {
    OrderRepository orderRepository;
    private OrderListRepository orderListRepository;

    @Autowired
    protected void setRepository(OrderRepository repository,OrderListRepository orderListRepository) {
        super.repository = repository;
        orderRepository = repository;
        this.orderListRepository = orderListRepository;
    }

    @Override
    public ClientOrder create(ClientOrder entity){
        int total = 0;
        entity.setInProgress(true);
        Optional<ClientOrder> cli = this.orderRepository.findByClient_IdAndInProgressEquals(entity.getClient().getId(),true);
        if(cli.isPresent() ){
            cli.get().setInProgress(false);
            super.edit(cli.get());
        }
        entity = super.create(entity);




        if(!entity.getOrderList().isEmpty()){
            for(OrderList o : entity.getOrderList()){
                total += o.getQuantity();
                o.setClientOrder(entity);
            }
            this.orderListRepository.saveAll(entity.getOrderList());
        }


        entity.setTotal(total);
        return entity;
    }



    public ClientOrder getClientOrder(int id){
        return this.orderRepository.findByClient_IdAndInProgressEquals(id,true).orElseThrow(()->new DataNotFounded("Client doesn't have any orders"));
    }
}
