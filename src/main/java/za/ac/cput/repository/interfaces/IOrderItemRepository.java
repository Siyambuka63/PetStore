package za.ac.cput.repository.interfaces;

import za.ac.cput.domain.OrderItem;
import java.util.List;

public interface IOrderItemRepository extends IRepository<OrderItem,Integer>{
    List<OrderItem> getAll();
}
