package za.ac.cput.service;

import org.springframework.data.repository.query.Param;
import za.ac.cput.domain.order.Order;
import za.ac.cput.domain.order.Status;
import za.ac.cput.domain.user.User;
import za.ac.cput.domain.user.wishlist.Wishlist;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface OrderService extends IService<Order,Long> {
    List<Order>getAll();

    Optional<Order> findByOrderDate(LocalDate orderDate);

    Optional<Order> findByDeliveryDate(LocalDate deliveryDate);

    Optional<Order> findByPrice(float price);

    Optional<Order> findByStatus(Status status);
    public List<Order> findbyContactEmail(@Param("email") String email);
    Order getActiveCart(User user);
    Order checkoutCart(User user);

}
