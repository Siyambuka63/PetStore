package za.ac.cput.repository.order;


import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.cput.domain.order.Order;

public interface OrderRepository  extends JpaRepository<Order, Integer> {

}
