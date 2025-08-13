// Nkheso Mathebula - 230762883
package za.ac.cput.service.user;

import za.ac.cput.domain.OrderItem;
import za.ac.cput.domain.OrderItemId;
import za.ac.cput.service.IService;

import java.util.List;

public interface OrderItemService extends IService<OrderItem, Long> {
    List<OrderItem> findByOrderId(Long orderId);
    List<OrderItem> findByProductId(Long productId);

}
