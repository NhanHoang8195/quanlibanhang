package da.java.common.service;

import da.java.common.entities.Order;

public interface OrderService {
    
    public Order saveOrder(String json);
    
    public Void deleteOrder(Long id);
}
