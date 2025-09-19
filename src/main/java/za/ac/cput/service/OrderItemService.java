// Nkheso Mathebula - 230762883
package za.ac.cput.service;

import za.ac.cput.domain.orderItem.OrderItem;
import za.ac.cput.domain.orderItem.OrderItemId;

import java.util.List;

public interface OrderItemService extends IService<OrderItem, OrderItemId> {
    public List<OrderItem> getAll();

    OrderItem updateQuantity(Long orderId, Long productId, int quantity);
}