package da.java.common.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import da.java.common.CommonUtil;
import da.java.common.entities.Order;
import da.java.common.enums.OrderStatus;
import da.java.common.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {
    
    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
    
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order saveOrder(String json) {
        logger.debug("IN - save Order");
        try {
            Order orderData = (Order) CommonUtil.getObjectFromJson(json, Order.class);
            
            if (orderData == null) {
                throw new Exception("Can not get object from json");
            }
            //set new order
            orderData.setOrderStatus(OrderStatus.NEW);
            orderData = orderRepository.save(orderData);
            if(orderData == null) {
                throw new Exception ("Can not save data");
            }
            return orderData;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        } finally {
            logger.debug("OUT - save Order");
        }
    }

    public Void deleteOrder(Long id) {
        orderRepository.delete(id);
        return null;
    }
}
