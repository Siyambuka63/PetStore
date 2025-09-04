package za.ac.cput.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.order.Order;
import za.ac.cput.domain.order.Status;
import za.ac.cput.domain.user.User;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findByOrderDate(LocalDate orderDate);

    Optional<Order> findByDeliveryDate(LocalDate deliveryDate);

    Optional<Order> findByPrice(float price);

    Optional<Order> findByStatus(Status status);

    Optional<Order> findByUserAndStatus(User user, Status status);

    Optional<Order> findByUserIdAndStatus(long user_id, Status status);
}
