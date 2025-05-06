package za.ac.cput.repository.interfaces;

import za.ac.cput.domain.Order;
import za.ac.cput.repository.OrderRepository;
import java.util.List;

public interface IOrderRepository extends IRepository<Order,Integer> {
    List<Order> getAll();
}
