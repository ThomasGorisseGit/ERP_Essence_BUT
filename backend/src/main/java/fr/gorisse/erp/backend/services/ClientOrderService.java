package fr.gorisse.erp.backend.services;

import fr.gorisse.erp.backend.entity.Client;
import fr.gorisse.erp.backend.entity.ClientOrder;
import fr.gorisse.erp.backend.entity.OrderList;
import fr.gorisse.erp.backend.entity.Product;
import fr.gorisse.erp.backend.exceptions.DataNotFounded;
import fr.gorisse.erp.backend.repository.OrderListRepository;
import fr.gorisse.erp.backend.repository.OrderRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClientOrderService extends ServiceMethods<ClientOrder> {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderListRepository orderListRepository;
    @Autowired
    private ProductService productService;
    @Override
    @Autowired
    protected void setRepository() {
        super.repository = orderRepository;
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
        return this.orderRepository.findByClient_Id(id).orElseThrow(()->new DataNotFounded("Client doesn't have any orders"));
    }
}
