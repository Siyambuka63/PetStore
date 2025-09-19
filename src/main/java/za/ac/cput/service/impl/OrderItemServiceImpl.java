// Nkheso Mathebula - 230762883
package za.ac.cput.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.orderItem.OrderItem;
import za.ac.cput.domain.orderItem.OrderItemId;
import za.ac.cput.repository.OrderItemRepository;
import za.ac.cput.repository.OrderRepository;
import za.ac.cput.service.OrderItemService;

import java.util.List;

@Service
public class OrderItemServiceImpl implements OrderItemService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private static OrderItemService service;

    private final OrderItemRepository repository;

    @Autowired
    public OrderItemServiceImpl(OrderItemRepository repository) {
        this.repository = repository;
    }

    @Override
    public OrderItem create(OrderItem orderItem) {
        return repository.save(orderItem);
    }

    @Override
    public OrderItem read(OrderItemId id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public OrderItem update(OrderItem orderItem) {
        return repository.save(orderItem);
    }

    @Override
    public void delete(OrderItemId id) {
        repository.deleteById(id);
    }

    @Override
    public List<OrderItem> getAll() {
        return repository.findAll();
    }

    public List<OrderItem> findByOrderId(Long orderId) {
        return repository.findByOrderId(orderId);
    }

    @Override
    public OrderItem updateQuantity(Long orderId, Long productId, int quantity) {
        OrderItemId id = new OrderItemId(orderId, productId);
        OrderItem item = repository.findById(id).orElseThrow(() -> new RuntimeException("Item not found in cart"));

        if (quantity <= 0) {
            repository.delete(item);
            return null;
        }

        OrderItem updated = new OrderItem.Builder()
                .copy(item)
                .setQuantity(quantity)
                .setTotalPrice(item.getPricePerItem() * quantity)
                .build();

        updated = repository.save(updated);

        return updated;
    }
}