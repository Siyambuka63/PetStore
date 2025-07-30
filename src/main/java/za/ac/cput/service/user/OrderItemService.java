// Nkheso Mathebula - 230762883
package za.ac.cput.service.user;

import za.ac.cput.domain.OrderItem;
import za.ac.cput.domain.OrderItemId;

import java.util.List;

public interface OrderItemService extends IService<OrderItem, OrderItemId> {
    List<OrderItem> findByOrderId(Long orderId);
    List<OrderItem> findByProductId(Long productId);

}
