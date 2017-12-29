package da.java.common.service;

import java.util.List;

import da.java.common.entities.Order;

public interface OrderService {
    
    public Order saveOrder(String json);
    
    public Void deleteOrder(Long id);
    public List<Order> getOrderList();
}
