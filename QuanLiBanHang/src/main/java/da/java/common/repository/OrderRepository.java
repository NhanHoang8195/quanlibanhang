package da.java.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import da.java.common.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
   
}
