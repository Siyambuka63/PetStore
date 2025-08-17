// Nkheso Mathebula - 230762883
package za.ac.cput.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.OrderItem;
import za.ac.cput.domain.OrderItemId;
import za.ac.cput.repository.OrderItemRepository;
import za.ac.cput.repository.ReviewRepository;
import za.ac.cput.service.OrderItemService;
import za.ac.cput.service.user.ReviewService;
import java.util.List;
import java.util.Optional;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    private OrderItemRepository repository;

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
        Optional<OrderItem> item = repository.findById(id);
        return item.orElse(null);
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

    @Override
    public List<OrderItem> findByOrderId(Long orderId) {
        return repository.findByOrder_Id(orderId);
    }

    @Override
    public List<OrderItem> findByProductId(Long productId) {
        return repository.findByProduct_Id(productId);
    }
}
