package za.ac.cput.repository.cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.cart.CartItem;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Integer> {

}
