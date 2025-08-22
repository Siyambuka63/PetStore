// Nkheso Mathebula - 230762883
package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.orderItem.OrderItem;
import za.ac.cput.domain.orderItem.OrderItemId;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemId> {}