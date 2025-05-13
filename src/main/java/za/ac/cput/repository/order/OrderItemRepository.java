package za.ac.cput.repository.order;


import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.cput.domain.order.OrderItem;

public interface OrderItemRepository  extends JpaRepository<OrderItem, Integer> {

}
