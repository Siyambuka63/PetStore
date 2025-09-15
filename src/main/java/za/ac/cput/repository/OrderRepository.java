package za.ac.cput.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.order.Order;
import za.ac.cput.domain.order.Status;
import za.ac.cput.domain.user.User;
import za.ac.cput.domain.user.wishlist.Wishlist;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findByOrderDate(LocalDate orderDate);

    Optional<Order> findByDeliveryDate(LocalDate deliveryDate);

    Optional<Order> findByPrice(float price);

    Optional<Order> findByStatus(Status status);

    Optional<Order> findByUserAndStatus(User user, Status status);

    Optional<Order> findByUserEmailAndStatus(String email, Status status);

    @Query("SELECT u FROM Order u WHERE u.user.email = :email")
    public List<Order> findbyContactEmail(@Param("email") String email);
}
